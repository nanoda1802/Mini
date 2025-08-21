package controller;

import java.util.Map;

import model.team.Member;
import model.team.Team;

public class TeamController implements Controller<Team> {

	Team team;
	Map<String, Member> members;
	long index = 1;

	public TeamController(Team team, Map<String, Member> members) {
		this.team = team;
		this.members = members;
	}

	@Override
	public void add(String[] infos) {
	}

	@Override
	public Team get(String eid) {
		return null;
	}

	@Override
	public void update(String[] changes) {
	}

	@Override
	public void remove(String eid) {
	}

}
