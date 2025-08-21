package model.project;

import java.util.HashMap;
import java.util.Map;

import controller.ProjectController;

public class Project {
	private static final Project _instance = new Project();
	private Map<String, Task> tasks = new HashMap<>();
	ProjectController pc = new ProjectController(_instance, tasks);

	public static Project getInstance() {
		return _instance;
	}
}
