package model.team;

import configs.team.Authority;
import model.project.Task;

import java.time.LocalDate;
import java.util.Set;

public class Member {
    private final LocalDate startDate = LocalDate.now();
    private String mid;
    private String name;
    private Authority auth;
    private Set<Task> tasks; // [메모] 멤버를 통해 접근할 Tasks는 각각에 접근할 필요가 없기 때문에 Set으로 지정

    public Member(String mid, String name, Authority auth) {
        this.mid = mid;
        this.name = name;
        this.auth = auth;
    }

    public String getMid() {
        return mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Authority getAuth() {
        return auth;
    }

    public void setAuth(Authority auth) {
        this.auth = auth;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
