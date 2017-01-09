package com.shailesh.rest.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shailesh.service.TournamentService;
import com.shailesh.viewobjects.ScheduleVO;
import com.shailesh.viewobjects.TeamVO;

@Controller
@RequestMapping("/torunamanent")
public class TournamentManager {

	@Autowired
	private TournamentService tournamentService;

	public TournamentManager() {
	}

	@RequestMapping("addteams")
	public void addTournamentTeams(@RequestBody Set<TeamVO> teams) {

		tournamentService.add(teams);

	}

	@RequestMapping("getallteams")
	public @ResponseBody Set<TeamVO> getTournamentTeams() {

		return tournamentService.getTournamentTeams();

	}

	@RequestMapping("getTeamsSchedule")
	public @ResponseBody List<ScheduleVO> getTeamsSchedule() {

		List<ScheduleVO> teamsSchedule = tournamentService.getTeamsSchedule();
		return teamsSchedule;

	}

}
