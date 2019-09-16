package com.shailesh.rest.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shailesh.service.TournamentService;
import com.shailesh.viewobjects.ScheduleVO;
import com.shailesh.viewobjects.TeamVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tournament")
@Slf4j
public class TournamentManager {

	@Autowired
	@Qualifier("tournamentService")
	private TournamentService tournamentService;

	public TournamentManager() {
	}

	@GetMapping("/addteams")
	public void addTournamentTeams(@RequestBody Set<TeamVO> teams) {

		tournamentService.add(teams);

	}

	@GetMapping("/getallteams")
	public @ResponseBody Set<TeamVO> getTournamentTeams() {
		
		log.info("I am in getTournamentTeams  for URL getallteams ");

		return tournamentService.getTournamentTeams();

	}

	@GetMapping("/getTeamsSchedule")
	public @ResponseBody List<ScheduleVO> getTeamsSchedule() {

		List<ScheduleVO> teamsSchedule = tournamentService.getTeamsSchedule();
		return teamsSchedule;

	}

}
