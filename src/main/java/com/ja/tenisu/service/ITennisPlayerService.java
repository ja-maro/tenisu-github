package com.ja.tenisu.service;

import java.util.List;

import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;

public interface ITennisPlayerService {

    List<TennisPlayerDTO> getAllPlayersWithoutDataByRank();

    TennisPlayer getPlayerById(int id);

}
