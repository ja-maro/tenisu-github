package com.ja.tenisu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.tenisu.model.TennisPlayersStatistics;
import com.ja.tenisu.service.IStatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    IStatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<TennisPlayersStatistics> getPlayersStatistics() {
        return ResponseEntity.ok(statisticsService.getTennisPlayersStatistics());
    }

}
