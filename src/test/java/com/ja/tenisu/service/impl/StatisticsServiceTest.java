package com.ja.tenisu.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ja.tenisu.dao.ITennisPlayerDAO;
import com.ja.tenisu.model.Country;
import com.ja.tenisu.model.Data;
import com.ja.tenisu.model.HeightWeight;
import com.ja.tenisu.model.TennisPlayer;

@SpringBootTest
public class StatisticsServiceTest {

    @Mock
    private ITennisPlayerDAO mockTennisPlayerDAO;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPlayersStatistics_WithData_ShouldReturnValues() {
        List<TennisPlayer> players = Arrays.asList(getMockPlayer1(), getMockPlayer2(), getMockPlayer3(),
                getMockPlayer4());
        when(mockTennisPlayerDAO.getAllPlayers()).thenReturn(players);
        when(mockTennisPlayerDAO.getAllHeightsAndWeights()).thenReturn(Arrays.asList(
                new HeightWeight(185, 80000),
                new HeightWeight(175, 70000),
                new HeightWeight(165, 60000),
                new HeightWeight(155, 50000)));
        when(mockTennisPlayerDAO.getAllHeights()).thenReturn(Arrays.asList(185, 175, 165, 155));

        assertEquals("SUI", statisticsService.getTennisPlayersStatistics().getHighestWinRatioCountry().getCode());
        assertEquals("22,27", statisticsService.getTennisPlayersStatistics().getAverageBMI());
        assertEquals("170", statisticsService.getTennisPlayersStatistics().getMedianHeight());
    }

    @Test
    public void getHighestWinRatioCountry_ShouldReturnSwiss() {
        List<TennisPlayer> players = Arrays.asList(getMockPlayer1(), getMockPlayer2(), getMockPlayer3(),
                getMockPlayer4());
        when(mockTennisPlayerDAO.getAllPlayers()).thenReturn(players);

        assertEquals("SUI", statisticsService.getHighestWinRatioCountry().getCode());
    }

    @Test
    public void getHighestWinRatioCountry_WhenNoSwiss_ShouldReturnUSA() {
        List<TennisPlayer> players = Arrays.asList(getMockPlayer1(), getMockPlayer2(), getMockPlayer3());
        when(mockTennisPlayerDAO.getAllPlayers()).thenReturn(players);

        assertEquals("USA", statisticsService.getHighestWinRatioCountry().getCode());
    }

    @Test
    public void getAverageBMI_ForGivenHeightsAndWeights_ShouldReturn22comma27() {
        List<HeightWeight> heightWeights = Arrays.asList(
                new HeightWeight(185, 80000),
                new HeightWeight(175, 70000),
                new HeightWeight(165, 60000),
                new HeightWeight(155, 50000));
        when(mockTennisPlayerDAO.getAllHeightsAndWeights()).thenReturn(heightWeights);

        assertEquals(22.27052273855486, statisticsService.getPlayersAverageBMI());
    }

    @Test
    public void getMedianHeight_ForEvenNumberOfHeights_ShouldReturn170() {
        when(mockTennisPlayerDAO.getAllHeights()).thenReturn(Arrays.asList(185, 175, 165, 155));

        assertEquals(170, statisticsService.getPlayersMedianHeight());
    }

    @Test
    public void getMedianHeight_ForOddNumberOfHeights_ShouldReturn165() {
        when(mockTennisPlayerDAO.getAllHeights()).thenReturn(Arrays.asList(175, 165, 155));

        assertEquals(165, statisticsService.getPlayersMedianHeight());
    }

    @Test
    public void getMedianHeight_ForEvenNumberOfHeightsAndNonIntegerMedian_ShouldReturn177comma5() {
        when(mockTennisPlayerDAO.getAllHeights()).thenReturn(Arrays.asList(185, 180, 175, 155));

        assertEquals(177.5, statisticsService.getPlayersMedianHeight());
    }

    private TennisPlayer getMockPlayer1() {
        TennisPlayer mockPlayer1 = new TennisPlayer(1, "John", "Doe", "J.DOE", 'M', new Country("USA.png", "USA"),
                "JohnDoe.png", new Data(200, 2000, 80000, 185, 30, Arrays.asList(0, 0, 0, 0, 0)));
        return mockPlayer1;
    }

    private TennisPlayer getMockPlayer2() {
        return new TennisPlayer(2, "Mary", "Jane", "M.JAN", 'F', new Country("USA.png", "USA"),
                "MaryJane.png",
                new Data(2, 20000, 60000, 165, 25, Arrays.asList(1, 1, 1, 1, 1)));
    }

    private TennisPlayer getMockPlayer3() {
        TennisPlayer mockPlayer3 = new TennisPlayer(3, "Tim", "Burton", "T.BUR", 'M', new Country("England.jpg", "ENG"),
                "TimBurton.png", new Data(33, 10000, 70000, 175, 35, Arrays.asList(0, 0, 0, 0, 1)));
        return mockPlayer3;
    }

    private TennisPlayer getMockPlayer4() {
        return new TennisPlayer(4, "Alice", "Cooper", "A.COO", 'F', new Country("Swiss.png", "SUI"),
                "AliceCooper.png", new Data(8, 4000, 50000, 155, 20, Arrays.asList(1, 1, 1, 1, 0)));
    }

}
