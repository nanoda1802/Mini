package model.team;

import java.util.Date;
import java.util.Set;

import configs.team.Authority;
import model.project.Task;

public class Member {
	private String mid;
	private String name;
	private Authority auth;
	private Set<Task> tasks;
	private final Date startDate = new Date();

	public Member(String mid, String name, Authority auth, Set<Task> tasks) {
		super();
		this.mid = mid;
		this.name = name;
		this.auth = auth;
		this.tasks = tasks;
	}
}
