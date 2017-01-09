package com.shailesh.repository;

import java.io.Serializable;

import com.shailesh.viewobjects.PlayerVO;

/**
 * This class will act for persistence layer and is not being used and placed as
 * a sample
 * 
 * @author Shailesh Chandra
 *
 */

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2308890769525755661L;

	/**
	 * 
	 */
	private String teamId;

	private String teamName;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public PlayerVO[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerVO[] players) {
		this.players = players;
	}

	private PlayerVO[] players;

	public String toString() {
		return new StringBuilder().append("Team id=").append(teamId).append(" Team Name=").append(teamName).toString();
	}

}
