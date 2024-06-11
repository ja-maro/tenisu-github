package com.ja.tenisu.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TennisPlayerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private final String CONTROLLER_PATH = "/players";

    @Test
    public void getPlayersWithoutDataByRank_ShouldReturn5PlayerDTOsInRankOrder() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].id", is(17)))
                .andExpect(jsonPath("$[0].rank", is(1)))
                .andExpect(jsonPath("$[1].id", is(52)))
                .andExpect(jsonPath("$[1].rank", is(2)))
                .andExpect(jsonPath("$[2].id", is(102)))
                .andExpect(jsonPath("$[2].rank", is(10)))
                .andExpect(jsonPath("$[3].id", is(65)))
                .andExpect(jsonPath("$[3].rank", is(21)))
                .andExpect(jsonPath("$[4].id", is(95)))
                .andExpect(jsonPath("$[4].rank", is(52)));
    }

    @Test
    public void getPlayerById_WhenIdExists_ShouldReturnPlayer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH + "/17"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(17)))
                .andExpect(jsonPath("$.firstname", is("Rafael")))
                .andExpect(jsonPath("$.lastname", is("Nadal")))
                .andExpect(jsonPath("$.shortname", is("R.NAD")))
                .andExpect(jsonPath("$.data.rank", is(1)))
                .andExpect(jsonPath("$.country.code", is("ESP")));
    }

}
