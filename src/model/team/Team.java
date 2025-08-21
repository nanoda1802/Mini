package model.team;

import java.util.HashMap;
import java.util.Map;

import controller.TeamController;

public class Team {
	private static final Team _instance = new Team();
	private Map<String, Member> members = new HashMap<>();
	private Member leader;
	TeamController tc = new TeamController(_instance, members);

	public static Team getInstance() {
		return _instance;
	}
}
