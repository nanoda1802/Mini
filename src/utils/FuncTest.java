package utils;

import model.project.Project;
import model.project.Task;
import model.team.Member;
import model.team.Team;
import utils.console.Viewer;

// [ FuncTest 클래스 설명 ]
// - FuncTest는 코드가 의도대로 작동하는지 확인하기 위한 클래스임다.
// - addDummy 메서드들은 각각 더미 Task와 Member 인스턴스를 생성할 수 있습니다.
// - dummyCount 필드를 통해 생성될 인스턴스의 개수를 조절할 수 있습니다.
// - show 메서드들은 각각 전체 프로젝트와 전체 팀의 데이터를 확인할 수 있습니다.

public class FuncTest {
    private static final int dummyCount = 15;

    /* Project에 더미 데이터 추가 */
    public static void addDummyTasks() {
        for (int i = 1; i <= dummyCount; i++) {
            String name = i < 10 ? "프로젝트0" + i : "프로젝트" + i;
            String type = i < 10 ? "2" : "4";
            Project.getInstance().controller.add(new String[]{name, type, "@", "20251225"});
        }
    }

    /* Project 현황 확인 */
    public static void showProject() {
        String tasks = Project.getInstance().controller.getAll().stream().map(Task::toString).reduce((acc, cur) -> acc + cur).orElseGet(() -> "!! project test 생성 실패 !!");

        Viewer.print(String.format("""
                [Task 목록]
                %s
                [현재 Task의 개수]
                %d
                """, tasks, Project.getInstance().controller.getAll().size()));
    }

    /* Team에 더미 데이터 추가 */
    public static void addDummyMembers() {
        for (int i = 1; i <= dummyCount; i++) {
            String name = i < 10 ? "팀원0" + i : "팀원" + i;
            Team.getInstance().controller.add(new String[]{name,(i%3)+1+""});
        }
    }

    /* Team 현황 확인 */
    public static void showTeam() {
        // [메모] Member 클래스에서 toString 메서드를 구현해야 작동합니다!
        String members = Team.getInstance().controller.getAll().stream().map(Member::toString).reduce((acc, cur) -> acc + cur).orElseGet(() -> "!! team test 문자열 생성 실패 !!");

        Viewer.print(String.format("""
                [Member 목록]
                %s
                [현재 Member의 개수]
                %d
                """, members, Team.getInstance().controller.getAll().size()));
    }
}
