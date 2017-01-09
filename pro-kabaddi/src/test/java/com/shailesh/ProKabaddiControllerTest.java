package com.shailesh;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shailesh.viewobjects.ScheduleVO;
import com.shailesh.viewobjects.TeamVO;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProKabaddiControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void start() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("Welcome to Pro Kabaddi")));

	}

	@Test
	public void addTeams() throws Exception {

		List<TeamVO> teams = new ArrayList<>();

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

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/torunamanent/addteams")
				.content(toJsonString(teams)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(requestBuilder);
	}

	@Test
	public void prepareSchedule() throws Exception {
		MvcResult andReturn = mvc
				.perform(
						MockMvcRequestBuilders.get("/torunamanent/getTeamsSchedule").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();
		System.out.println(contentAsString);

		ObjectMapper mapper = new ObjectMapper();
		List<ScheduleVO> matchSchedules = mapper.readValue(contentAsString,
				TypeFactory.defaultInstance().constructCollectionType(List.class, ScheduleVO.class));

		System.out.println(matchSchedules);

		Assert.assertTrue(matchSchedules.size() > 0);

	}

	public static String toJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
