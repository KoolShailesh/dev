package com.shailesh.util;

import java.io.Serializable;

public class Pair<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6271852329893708219L;
	T team1;

	public T getTeam1() {
		return team1;
	}

	public T getTeam2() {
		return team2;
	}

	T team2;

	public Pair(T team1, T team2) {
		this.team1 = team1;
		this.team2 = team2;
	}

	public Pair() {

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("Team1=").append(team1);
		builder.append(" Team2=").append(team2);
		builder.append("]");

		return builder.toString();

	}

}
