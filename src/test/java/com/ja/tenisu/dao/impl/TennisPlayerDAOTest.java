package com.ja.tenisu.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ja.tenisu.dao.NotFoundException;
import com.ja.tenisu.model.Country;
import com.ja.tenisu.model.Data;
import com.ja.tenisu.model.HeightWeight;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class TennisPlayerDAOTest {

    @Test
    public void test() {
        assertTrue(true);
    }

    private TennisPlayerDAO tennisPlayerDAO;

    @BeforeEach
    public void setUp() throws IOException {

        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        tennisPlayerDAO = new TennisPlayerDAO(mockObjectMapper);

        TennisPlayer mockPlayer1 = new TennisPlayer(1, "John", "Doe", "J.DOE", 'M', new Country("USA.png", "USA"),
                "JohnDoe.png", new Data(200, 2000, 80, 185, 30, Arrays.asList(0, 0, 0, 0, 0)));
        TennisPlayer mockPlayer2 = new TennisPlayer(2, "Mary", "Jane", "M.JAN", 'F', new Country("USA.png", "USA"),
                "MaryJane.png",
                new Data(2, 20000, 60, 165, 25, Arrays.asList(1, 1, 1, 1, 1)));
        TennisPlayer mockPlayer3 = new TennisPlayer(3, "Tim", "Burton", "T.BUR", 'M', new Country("England.jpg", "ENG"),
                "TimBurton.png", new Data(33, 10000, 70, 175, 35, Arrays.asList(0, 0, 0, 0, 1)));
        TennisPlayer mockPlayer4 = new TennisPlayer(4, "Alice", "Cooper", "A.COO", 'F', new Country("Swiss.png", "SUI"),
                "AliceCooper.png", new Data(8, 4000, 50, 155, 20, Arrays.asList(1, 1, 1, 1, 0)));

        List<TennisPlayer> mockPlayers = new ArrayList<>(
                Arrays.asList(mockPlayer1, mockPlayer2, mockPlayer3, mockPlayer4));

        Mockito.when(mockObjectMapper.readValue(any(InputStream.class), eq(TennisPlayersWrapper.class)))
                .thenReturn(new TennisPlayersWrapper(mockPlayers));

        InputStream jsonResource = new ClassPathResource(TennisPlayerDAO.JSON_DATA_FILE).getInputStream();
        Mockito.when(mockObjectMapper.readValue(jsonResource, TennisPlayersWrapper.class))
                .thenReturn(new TennisPlayersWrapper(mockPlayers));

        tennisPlayerDAO.init();

    }

    @Test
    public void GetAllPlayers_ShouldReturn4Players() {
        List<TennisPlayer> players = tennisPlayerDAO.getAllPlayers();
        assertNotNull(players);
        assertEquals(4, players.size());
    }

    @Test
    public void GetAllPlayers_ShouldReturnPlayersWithFirstNameJohnAndMaryAndTimAndAlice() {
        List<TennisPlayer> players = tennisPlayerDAO.getAllPlayers();
        List<String> playerNames = players.stream()
                .map(TennisPlayer::getFirstName)
                .collect(Collectors.toList());

        assertTrue(playerNames.contains("John"));
        assertTrue(playerNames.contains("Mary"));
        assertTrue(playerNames.contains("Tim"));
        assertTrue(playerNames.contains("Alice"));
    }

    @Test
    public void GetAllPlayers_WhenPlayersEmpty_ShouldThrowNotFoundException() throws IOException {
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        tennisPlayerDAO = new TennisPlayerDAO(mockObjectMapper);
        Mockito.when(mockObjectMapper.readValue(new File(TennisPlayerDAO.JSON_DATA_FILE), TennisPlayersWrapper.class))
                .thenReturn(new TennisPlayersWrapper(new ArrayList<TennisPlayer>()));

        Exception e = assertThrows(NotFoundException.class, () -> {
            tennisPlayerDAO.getAllPlayers();
        });
        assertEquals("No players found", e.getMessage());
    }

    @Test
    public void GetAllPlayersWithoutDataByRank_ShouldReturn4PlayerDTOs() {
        List<TennisPlayerDTO> players = tennisPlayerDAO.getAllPlayersWithoutDataByRank();
        assertNotNull(players);
        assertEquals(4, players.size());
    }

    @Test
    public void GetAllPlayersWithoutDataByRank_ReturnsMaryThenAliceThenTimThenJohn() {
        List<TennisPlayerDTO> players = tennisPlayerDAO.getAllPlayersWithoutDataByRank();
        assertEquals("Mary", players.get(0).getFirstName());
        assertEquals("Alice", players.get(1).getFirstName());
        assertEquals("Tim", players.get(2).getFirstName());
        assertEquals("John", players.get(3).getFirstName());
    }

    @Test
    public void GetAllPlayersWithoutDataByRank_WhenPlayersEmpty_ShouldThrowNotFoundException() throws IOException {
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        tennisPlayerDAO = new TennisPlayerDAO(mockObjectMapper);
        Mockito.when(mockObjectMapper.readValue(new File(TennisPlayerDAO.JSON_DATA_FILE), TennisPlayersWrapper.class))
                .thenReturn(new TennisPlayersWrapper(new ArrayList<TennisPlayer>()));

        Exception e = assertThrows(NotFoundException.class, () -> {
            tennisPlayerDAO.getAllPlayersWithoutDataByRank();
        });
        assertEquals("No players found", e.getMessage());
    }

    @Test
    public void GetPlayerById_WhenIdIs3_ShouldReturnTimBurton() {
        TennisPlayer player = tennisPlayerDAO.getPlayerById(3);
        assertNotNull(player);
        assertEquals(3, player.getId());
        assertEquals("Tim", player.getFirstName());
        assertEquals("Burton", player.getLastName());
    }

    @Test
    public void GetPlayerById_WhenIdNotExists_ShouldThrowNotFoundException() {
        Exception e = assertThrows(NotFoundException.class, () -> {
            tennisPlayerDAO.getPlayerById(15);
        });
        assertEquals("Player with id 15 not found", e.getMessage());
    }

    @Test
    public void GetAllHeightsAndWeights_ShouldReturn4Items() {
        List<HeightWeight> heightsAndWeights = tennisPlayerDAO.getAllHeightsAndWeights();
        assertNotNull(heightsAndWeights);
        assertEquals(4, heightsAndWeights.size());
    }

    @Test
    public void GetAllHeightsAndWeights_ShouldReturnCorrectPairs() {
        List<HeightWeight> heightsAndWeights = tennisPlayerDAO.getAllHeightsAndWeights();

        assertTrue(heightsAndWeights.contains(new HeightWeight(185, 80)));
        assertTrue(heightsAndWeights.contains(new HeightWeight(165, 60)));
        assertTrue(heightsAndWeights.contains(new HeightWeight(175, 70)));
        assertTrue(heightsAndWeights.contains(new HeightWeight(155, 50)));
    }

    @Test
    public void GetAllHeightsAndWeights_WhenPlayersEmpty_ShouldThrowNotFoundException() throws IOException {
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        tennisPlayerDAO = new TennisPlayerDAO(mockObjectMapper);
        Mockito.when(mockObjectMapper.readValue(new File(TennisPlayerDAO.JSON_DATA_FILE), TennisPlayersWrapper.class))
                .thenReturn(new TennisPlayersWrapper(new ArrayList<TennisPlayer>()));

        Exception e = assertThrows(NotFoundException.class, () -> {
            tennisPlayerDAO.getAllHeightsAndWeights();
        });
        assertEquals("No players found", e.getMessage());
    }

    @Test
    public void GetAllHeights_ShouldReturn4Items() {
        List<Integer> heights = tennisPlayerDAO.getAllHeights();
        assertNotNull(heights);
        assertEquals(4, heights.size());
    }

    @Test
    public void GetAllHeights_ShouldReturnCorrectValues() {
        List<Integer> heights = tennisPlayerDAO.getAllHeights();

        assertTrue(heights.contains(185));
        assertTrue(heights.contains(175));
        assertTrue(heights.contains(165));
        assertTrue(heights.contains(155));
    }

    @Test
    public void GetAllHeights_WhenPlayersEmpty_ShouldThrowNotFoundException() throws IOException {
        ObjectMapper mockObjectMapper = Mockito.mock(ObjectMapper.class);
        tennisPlayerDAO = new TennisPlayerDAO(mockObjectMapper);
        Mockito.when(mockObjectMapper.readValue(new File(TennisPlayerDAO.JSON_DATA_FILE), TennisPlayersWrapper.class))
                .thenReturn(new TennisPlayersWrapper(new ArrayList<TennisPlayer>()));

        Exception e = assertThrows(NotFoundException.class, () -> {
            tennisPlayerDAO.getAllHeights();
        });
        assertEquals("No players found", e.getMessage());
    }
}
