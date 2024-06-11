package com.ja.tenisu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;
import com.ja.tenisu.service.ITennisPlayerService;

@RestController
@RequestMapping("/players")
public class TennisPlayerController {

    @Autowired
    ITennisPlayerService tennisPlayerService;

    @GetMapping
    public ResponseEntity<List<TennisPlayerDTO>> getPlayersWithoutDataByRank() {
        return ResponseEntity.ok(tennisPlayerService.getAllPlayersWithoutDataByRank());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TennisPlayer> getPlayerById(@PathVariable int id) {
        return ResponseEntity.ok(tennisPlayerService.getPlayerById(id));
    }

}
