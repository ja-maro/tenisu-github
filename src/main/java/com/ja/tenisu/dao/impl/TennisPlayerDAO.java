package com.ja.tenisu.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ja.tenisu.dao.ITennisPlayerDAO;
import com.ja.tenisu.dao.NotFoundException;
import com.ja.tenisu.model.HeightWeight;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;

@Service
public class TennisPlayerDAO implements ITennisPlayerDAO {

    // public static final String JSON_DATA_FILE =
    // "src/main/resources/headtohead.json";
    public static final String JSON_DATA_FILE = "headtohead.json";
    @Value("classpath:" + JSON_DATA_FILE)
    Resource resourceFile;

    private ObjectMapper objectMapper;
    private List<TennisPlayer> players;

    public TennisPlayerDAO() {
        this.objectMapper = new ObjectMapper();
    }

    public TennisPlayerDAO(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        InputStream jsonResource = new ClassPathResource(JSON_DATA_FILE).getInputStream();
        TennisPlayersWrapper wrapper = objectMapper.readValue(jsonResource, TennisPlayersWrapper.class);
        players = wrapper.getPlayers();
    }

    @Override
    public List<TennisPlayer> getAllPlayers() {
        throwNotFoundExceptionIfPlayersEmpty();
        return players;
    }

    @Override
    public List<TennisPlayerDTO> getAllPlayersWithoutDataByRank() {
        throwNotFoundExceptionIfPlayersEmpty();
        return players.stream()
                .sorted((o1, o2) -> Integer.compare(o1.getRank(), o2.getRank()))
                .map(player -> new TennisPlayerDTO(player)).collect(Collectors.toList());
    }

    @Override
    public TennisPlayer getPlayerById(int id) {
        return players.stream().filter(player -> player.getId() == id).findFirst()
                .orElseThrow(() -> new NotFoundException("Player with id " + id + " not found"));
    }

    @Override
    public List<HeightWeight> getAllHeightsAndWeights() {
        throwNotFoundExceptionIfPlayersEmpty();
        return players.stream().map(player -> new HeightWeight(player)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAllHeights() {
        throwNotFoundExceptionIfPlayersEmpty();
        return players.stream().map(player -> player.getHeight()).collect(Collectors.toList());
    }

    private void throwNotFoundExceptionIfPlayersEmpty() {
        if (players == null || players.isEmpty())
            throw new NotFoundException("No players found");
    }

}
