package model.project;

import java.util.Date;

import configs.project.TaskStatus;
import configs.project.TaskType;
import model.team.Member;

public class Task {
	private String tid;
	private String name;
	private TaskType type;
	private TaskStatus status = TaskStatus.NOT_STARTED;
	private Member pic;
	private final Date createdAt = new Date();
	private Date updatedAt = new Date();
	private Date dueTo;

	public Task(String tid, String name, TaskType type, Member pic, Date dueTo) {
		this.tid = tid;
		this.name = name;
		this.type = type;
		this.pic = pic;
		this.dueTo = dueTo;
	}

}
