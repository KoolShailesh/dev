package com.shailesh.viewobjects;

import java.io.Serializable;

public class PlayerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6464025069667451848L;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	private String playerId;

	private String PlayerName;

}
