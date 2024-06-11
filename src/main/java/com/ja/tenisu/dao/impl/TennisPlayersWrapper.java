package com.ja.tenisu.dao.impl;

import java.util.List;

import com.ja.tenisu.model.TennisPlayer;

public class TennisPlayersWrapper {
    private List<TennisPlayer> players;

    public TennisPlayersWrapper() {
    }

    public TennisPlayersWrapper(List<TennisPlayer> players) {
        this.players = players;
    }

    public List<TennisPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<TennisPlayer> players) {
        this.players = players;
    }
}
