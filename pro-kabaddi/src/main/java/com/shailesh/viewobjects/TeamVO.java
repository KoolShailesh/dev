package com.shailesh.viewobjects;

import java.io.Serializable;

public class TeamVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2308890769525755661L;

	/**
	 * 
	 */
	private String teamId;

	private String teamName;

	private String teamHomeLocation;

	private PlayerVO[] players;

	public PlayerVO[] getPlayers() {
		return players;
	}

	public String getTeamHomeLocation() {
		return teamHomeLocation;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setPlayers(PlayerVO[] players) {
		this.players = players;
	}

	public void setTeamHomeLocation(String teamHomeLocation) {
		this.teamHomeLocation = teamHomeLocation;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Team id=").append(teamId).append(" Team Name=").append(teamName).toString();
	}

}
