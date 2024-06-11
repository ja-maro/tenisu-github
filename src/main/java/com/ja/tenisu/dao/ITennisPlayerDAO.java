package com.ja.tenisu.dao;

import java.util.List;

import com.ja.tenisu.model.HeightWeight;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;

public interface ITennisPlayerDAO {

    List<TennisPlayer> getAllPlayers();

    List<TennisPlayerDTO> getAllPlayersWithoutDataByRank();

    TennisPlayer getPlayerById(int id);

    List<HeightWeight> getAllHeightsAndWeights();

    List<Integer> getAllHeights();
}
