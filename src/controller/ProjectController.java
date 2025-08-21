package controller;

import java.util.Map;

import model.project.Project;
import model.project.Task;

public class ProjectController implements Controller<Task> {

	Project project;
	Map<String, Task> tasks;
	long index = 1;

	public ProjectController(Project project, Map<String, Task> tasks) {
		this.project = project;
		this.tasks = tasks;
	}

	@Override
	public void add(String[] infos) {
	}

	@Override
	public Task get(String tid) {
		return null;
	}

	@Override
	public void update(String[] changes) {
	}

	@Override
	public void remove(String tid) {
	}

}
