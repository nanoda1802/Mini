package repository;

import configs.message.Ingredient;
import model.project.Task;
import model.team.Member;
import utils.LogRecorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectTeamRepository {
    private static final ProjectTeamRepository instance = new ProjectTeamRepository();
    private ProjectTeamRepository() {}
    public static ProjectTeamRepository getInstance() { return instance; }

    public void add(String projectId, String memberId) throws SQLException {
        String sql = "INSERT INTO project_team (pid, mid) VALUES (?, ?)";
        try (Connection conn = MakeConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        }
    }
    public void updateProjectTeamByProjectIds(String memberID, Set<String> newProjectIds) throws SQLException {
        Set<String> oldProjectIds = findProjectbyMember(memberID)
                .stream().map(Task::getTid).collect(Collectors.toSet());

        for (String oldProjectId : oldProjectIds) {
            if (!newProjectIds.contains(oldProjectId)) {
                remove(oldProjectId, memberID);
            }
        }
        for(String newProjectId : newProjectIds){
            if (!oldProjectIds.contains(newProjectId)){
                add(newProjectId,memberID);
            }
        }
    }
    public void updateProjectTeamByMemberIds(String projectID,Set<String> newMemberIds) throws SQLException{
        Set<String> oldMemberIds = findMemberbyProject(projectID)
                .stream().map(Member::getMid).collect(Collectors.toSet());
        for (String oldMemberId : oldMemberIds){
            if(!newMemberIds.contains(oldMemberId)) {
                remove(projectID, oldMemberId);
            }
        }
        for (String newMemberId:newMemberIds){
            if(!oldMemberIds.contains(newMemberId)){
                add(projectID,newMemberId);
            }
        }
    }
    public void remove(String projectId, String memberId) throws SQLException {
        String sql = "DELETE FROM project_team WHERE pid = ? AND mid = ?";
        try (Connection conn = MakeConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            pstmt.setString(2, memberId);
            pstmt.executeUpdate();
        }
    }
    public Set<Task> findProjectbyMember(String memberId) throws SQLException {
        String sql = "SELECT * FROM project_team WHERE mid = ?";
        Set<Task> tasks = new HashSet<>();
        try (Connection conn = MakeConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String taskID  = rs.getString("pid");
                Task task = ProjectRepository.getInstance().findById(taskID);
                tasks.add(task);
            }
        }
        return tasks;
    }
    public Set<Member> findMemberbyProject(String projectId) throws SQLException {
        String sql = "SELECT * FROM project_team WHERE pid = ?";
        Set<Member> members = new HashSet<>();
        try (Connection conn = MakeConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String memberID = rs.getString("mid");
                Member member = MemberRepository.getInstance().findById(memberID);
                members.add(member);
            }
        }
        return members;
    }

    public boolean exists(String projectId, String memberId) {
        String sql = "SELECT * FROM project_team WHERE pid = ? AND mid = ?";
        try (Connection conn = MakeConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, projectId);
            pstmt.setString(2, memberId);
             ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            LogRecorder.record(Ingredient.LOG_ERROR_SQL,"ProjectTeam 존재 검사");
            return false;
        }
    }

}

