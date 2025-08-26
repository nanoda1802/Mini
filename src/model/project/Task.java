package model.project;

import configs.project.TaskStatus;
import configs.project.TaskType;
import model.team.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private final LocalDate createdAt = LocalDate.now();
    private String tid;
    private String name;
    private TaskType type;
    private TaskStatus status = TaskStatus.NOT_STARTED;
    private Member assignee;
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDate dueTo;

    public Task(String tid, String name, TaskType type, Member assignee, LocalDate dueTo) {
        this.tid = tid;
        this.name = name;
        this.type = type;
        this.assignee = assignee;
        this.dueTo = dueTo;
    }

    public String getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Member getAssignee() {
        return assignee;
    }

    public void setAssignee(Member assignee) {
        this.assignee = assignee;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDate getDueTo() {
        return dueTo;
    }

    public void setDueTo(LocalDate dueTo) {
        this.dueTo = dueTo;
    }

    public void updateTime() {
        this.updatedAt = LocalDateTime.now();
    }

    // [메모] 기능 test 용도
    @Override
    public String toString() {
        return "\n< "+tid +" >"+ "\n업무명 : "+name + "\n유형 : "+type + "\n상태 : "+status + "\n담당자 : "+assignee +"\n생성일 : "+createdAt +"\n수정일 : "+updatedAt + "\n마감일 : "+dueTo;
    }
}
