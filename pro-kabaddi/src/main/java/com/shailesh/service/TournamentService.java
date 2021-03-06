package com.shailesh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shailesh.util.Pair;
import com.shailesh.viewobjects.ScheduleVO;
import com.shailesh.viewobjects.TeamVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Shailesh Chandra
 *
 */
@Service("tournamentService")
@Slf4j
public class TournamentService {

	private Map<String, Set<TeamVO>> localCache = null;

	private List<ScheduleVO> schedules = new ArrayList<>();

	public void add(Set<TeamVO> teams) {
		System.out.println();
		localCache.put("allteams", teams);

	}

	public Set<TeamVO> getTournamentTeams() {
		log.info("all teams from localCache {}",localCache);
		return localCache.get("allteams");
	}

	public TournamentService() {
		
		 localCache = new HashMap<>();
		
		log.info("I am in constructor");
		Set<TeamVO> teams = new HashSet<>();

		{
			TeamVO team1 = new TeamVO();
			team1.setTeamId("T1");
			team1.setTeamName("Telgu Titans");
			team1.setTeamHomeLocation("HYD");

			teams.add(team1);
		}

		{
			TeamVO team2 = new TeamVO();
			team2.setTeamId("T2");
			team2.setTeamName("Bengaluru Bulls");
			team2.setTeamHomeLocation("BLR");
			teams.add(team2);
		}
		{
			TeamVO team3 = new TeamVO();
			team3.setTeamId("T3");
			team3.setTeamName("Dabang Delhi");
			team3.setTeamHomeLocation("DEL");
			teams.add(team3);
		}

		TeamVO team4 = new TeamVO();
		team4.setTeamId("T4");
		team4.setTeamName("Pune Paltan");
		team4.setTeamHomeLocation("PNQ");
		teams.add(team4);

		TeamVO team5 = new TeamVO();
		team5.setTeamId("T5");
		team5.setTeamName("Chennal Kings");
		team5.setTeamHomeLocation("CHN");
		teams.add(team5);

		TeamVO team6 = new TeamVO();
		team6.setTeamId("T6");
		team6.setTeamName("Chandigarh Warrior");
		team6.setTeamHomeLocation("CHD");
		teams.add(team6);

		TeamVO team7 = new TeamVO();
		team7.setTeamId("T7");
		team7.setTeamName("Kolkatta Rockers");
		team7.setTeamHomeLocation("KOL");
		teams.add(team7);

		TeamVO team8 = new TeamVO();
		team8.setTeamId("T8");
		team8.setTeamName("Lucknow Royals");
		team8.setTeamHomeLocation("LKO");
		teams.add(team8);
		
		localCache.put("allteams", teams);
		
		log.info("localCache in consturctor {} ",localCache );
	}

	public List<ScheduleVO> getTeamsSchedule() {

		List<TeamVO> teams = new ArrayList<>(localCache.get("allteams"));
		int totalTeams = teams.size();

		System.out.println("totalTeams : " + totalTeams);

		List<Pair<TeamVO>> teamPairs = new ArrayList<>();

		// creating team pairs for two teams
		teams.stream().forEach(team1 -> teams.stream().forEach(team2 -> teamPairs.add(new Pair<TeamVO>(team1, team2))));

		// removing pair which have same team has opponents, and creating unique
		// pairs for teams
		List<Pair<TeamVO>> uniqueTeamPairs = teamPairs.stream()
				.filter(t -> !t.getTeam1().getTeamId().equals(t.getTeam2().getTeamId())).collect(Collectors.toList());

		System.out.println("Unique Teams Pairs: " + uniqueTeamPairs.size());

		int playDay = 0;
		// uniqueTeamPairs.forEach(System.out::println);
		while (!uniqueTeamPairs.isEmpty()) {

			boolean slot1Filled = false;

			playDay += 1;
			// For Slot 1
			SLOT1: for (int i = 0; i < uniqueTeamPairs.size(); i++) {
				Pair<TeamVO> pair = uniqueTeamPairs.get(i);

				boolean canTeamsPlay = canTeamsPlay(pair);
				if (canTeamsPlay) {
					ScheduleVO scheduleVO = new ScheduleVO();
					scheduleVO.setTeams(pair);
					String teamHomeLocation = pair.getTeam1().getTeamHomeLocation();
					scheduleVO.setMatchLocation(teamHomeLocation);
					scheduleVO.setPlayDay(playDay);
					scheduleVO.setSlot(1);

					schedules.add(scheduleVO);

					uniqueTeamPairs.remove(i);
					slot1Filled = true;
					break SLOT1;

				}

			}
			if (!slot1Filled) {

				// No match for slot 1
				ScheduleVO emptyScheduleVO = new ScheduleVO();
				emptyScheduleVO.setPlayDay(playDay);
				emptyScheduleVO.setSlot(1);
				schedules.add(emptyScheduleVO);

				// No match for slot 1
				ScheduleVO emptyScheduleVO2 = new ScheduleVO();
				emptyScheduleVO2.setPlayDay(playDay);
				emptyScheduleVO2.setSlot(2);
				schedules.add(emptyScheduleVO2);
			}

			else {
				// For Slot 2
				boolean slot2Filled = false;
				SLOT2: for (int i = 0; i < uniqueTeamPairs.size(); i++) {
					Pair<TeamVO> pair = uniqueTeamPairs.get(i);

					boolean canTeamsPlay = canTeamsPlay(pair);
					if (canTeamsPlay) {
						ScheduleVO scheduleVO = new ScheduleVO();
						scheduleVO.setTeams(pair);
						String teamHomeLocation = pair.getTeam1().getTeamHomeLocation();
						scheduleVO.setMatchLocation(teamHomeLocation);
						scheduleVO.setPlayDay(playDay);
						// scheduleVO.setSlot((schedules.size() % 2) == 0 ? 1 :
						// 2);
						scheduleVO.setSlot(2);
						schedules.add(scheduleVO);

						uniqueTeamPairs.remove(i);
						slot2Filled = true;
						break SLOT2;

					}
				}

				if (!slot2Filled) {
					ScheduleVO emptyScheduleVO = new ScheduleVO();

					emptyScheduleVO.setPlayDay(playDay);
					emptyScheduleVO.setSlot(2);
					schedules.add(emptyScheduleVO);
				}
			}
		}

		schedules.forEach(System.out::println);

		return schedules;
	}

	private boolean canTeamsPlay(Pair<TeamVO> currentTeamPair) {

		int size = schedules.size();

		if ((size == 0)) {
			return true;
		} else {
			ArrayList<ScheduleVO> arrayList = null;
			if (size % 2 == 0) {// compare last 2
				arrayList = new ArrayList<>(schedules.subList(size - 2, size));
				// System.out.println(arrayList);

			} else {// comparing last 3 else 1
				if (size >= 3) {
					arrayList = new ArrayList<>(schedules.subList(size - 3, size));
					// System.out.println(arrayList);
				} else {

					arrayList = new ArrayList<>(schedules.subList(size - 1, size));
					// System.out.println(arrayList);

				}

			}

			return validateAgainsTeams(arrayList, currentTeamPair);

		}

		// return false;
	}

	public boolean validateAgainsTeams(List<ScheduleVO> scheduleVOs, Pair<TeamVO> pair) {

		TeamVO team1 = pair.getTeam1();
		TeamVO team2 = pair.getTeam2();

		for (Iterator<ScheduleVO> iterator = scheduleVOs.iterator(); iterator.hasNext();) {
			ScheduleVO scheduleVO = iterator.next();

			if (scheduleVO.isEmpty()) {
				continue;
			}

			TeamVO playingTeam1 = scheduleVO.getPlayingTeams().getTeam1();
			TeamVO playingTeam2 = scheduleVO.getPlayingTeams().getTeam2();

			if (playingTeam1.getTeamId().equals(team1.getTeamId()) || playingTeam1.getTeamId().equals(team2.getTeamId())
					|| playingTeam2.getTeamId().equals(team1.getTeamId())
					|| playingTeam2.getTeamId().equals(team2.getTeamId())) {
				return false;
			}

		}

		return true;
	}


}
