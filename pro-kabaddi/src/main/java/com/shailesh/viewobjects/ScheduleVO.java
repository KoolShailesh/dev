package com.shailesh.viewobjects;

import java.io.Serializable;

import com.shailesh.util.Pair;

/**
 * 
 * @author Shailesh Chandra
 *
 */
public class ScheduleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2271462359831797522L;

	private Pair<TeamVO> playingTeams;

	private String matchLocation;

	private int playDay;

	private int slot;

	// private boolean empty;

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public Pair<TeamVO> getPlayingTeams() {
		return playingTeams;
	}

	public void setTeams(Pair<TeamVO> teams) {
		this.playingTeams = teams;
	}

	public String getMatchLocation() {
		return matchLocation;
	}

	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	public int getPlayDay() {
		return playDay;
	}

	public void setPlayDay(int playDay) {
		this.playDay = playDay;
	}

//	@JsonIgnore
	public boolean isEmpty() {

		return playingTeams == null;

	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		if (isEmpty()) {
			builder.append("NO Match").append(" day= ").append(playDay).append(" slot=").append(slot);
		} else {

			builder.append(playingTeams.getTeam1().getTeamName()).append(" vs ")
					.append(playingTeams.getTeam2().getTeamName()).append(" -").append(matchLocation).append(" day= ")
					.append(playDay).append(" slot=").append(slot);

		}
		return builder.toString();

	}
}
