package model.team;

import controller.controllers.TeamController;

import java.util.HashMap;
import java.util.Map;

public class Team {
    private static final Team _instance = new Team();
    private Map<String, Member> members = new HashMap<>();
    public TeamController controller = new TeamController(members);
    private Member leader;

    public static Team getInstance() {
        return _instance;
    }
}
