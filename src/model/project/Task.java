package model.project;

import configs.project.TaskStatus;
import configs.project.TaskType;
import model.team.Member;

import java.time.LocalDateTime;

public class Task {
    private final LocalDateTime createdAt = LocalDateTime.now();
    private String tid;
    private String name;
    private TaskType type;
    private TaskStatus status = TaskStatus.NOT_STARTED;
    private Member assignee;
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime dueTo;

    public Task(String tid, String name, TaskType type, Member assignee, LocalDateTime dueTo) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDueTo() {
        return dueTo;
    }

    public void setDueTo(LocalDateTime dueTo) {
        this.dueTo = dueTo;
    }
}
