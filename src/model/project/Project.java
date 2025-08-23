package model.project;

import controller.controllers.ProjectController;

import java.util.HashMap;
import java.util.Map;

public class Project {
    private static final Project _instance = new Project();
    private Map<String, Task> tasks = new HashMap<>();
    public ProjectController controller = new ProjectController(tasks);

    public static Project getInstance() {
        return _instance;
    }
}
