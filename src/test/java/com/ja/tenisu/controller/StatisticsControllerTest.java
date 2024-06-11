package com.ja.tenisu.controller;

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
public class StatisticsControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private final String CONTROLLER_PATH = "/statistics";

    @Test
    public void getPlayersStatistics_ShouldReturnStatistics() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.highestWinRatioCountry.code", is("SRB")))
                .andExpect(jsonPath("$.averageBMI", is("23,36")))
                .andExpect(jsonPath("$.medianHeight", is("185")));
    }

}
