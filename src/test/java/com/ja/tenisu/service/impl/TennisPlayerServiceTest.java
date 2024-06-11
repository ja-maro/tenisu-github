package com.ja.tenisu.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.ja.tenisu.dao.NotFoundException;
import com.ja.tenisu.model.Country;
import com.ja.tenisu.model.Data;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;

@SpringBootTest
public class TennisPlayerServiceTest {

    @Mock
    private ITennisPlayerDAO mockTennisPlayerDAO;

    @InjectMocks
    private TennisPlayerService tennisPlayerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GetPlayersWithoutDataByRank_ReturnsMaryThenAliceThenTimThenJohn() {
        List<TennisPlayerDTO> expectedPlayers = getExpectedPlayerDTOs();

        when(mockTennisPlayerDAO.getAllPlayersWithoutDataByRank()).thenReturn(expectedPlayers);

        List<TennisPlayerDTO> actualPlayers = tennisPlayerService.getAllPlayersWithoutDataByRank();

        assertNotNull(actualPlayers);
        assertEquals(expectedPlayers.size(), actualPlayers.size());
        assertEquals("Mary", actualPlayers.get(0).getFirstName());
        assertEquals("Alice", actualPlayers.get(1).getFirstName());
        assertEquals("Tim", actualPlayers.get(2).getFirstName());
        assertEquals("John", actualPlayers.get(3).getFirstName());
    }

    @Test
    public void GetPlayersWithoutDataByRank_WhenDaoThrowsNotFoundException_ShouldThrowNotFoundException() {

        when(mockTennisPlayerDAO.getAllPlayersWithoutDataByRank())
                .thenThrow(new NotFoundException("Players not found"));

        Exception e = assertThrows(NotFoundException.class, () -> {
            List<TennisPlayerDTO> playerDTOs = tennisPlayerService.getAllPlayersWithoutDataByRank();
        });
        assertEquals("Players not found", e.getMessage());
    }

    @Test
    public void testGetPlayerById_WhenDaoThrowsNotFoundException_ShouldReturnTimBurton() {
        TennisPlayer expectedPlayer = getMockPlayer3();
        int playerId = 3;

        when(mockTennisPlayerDAO.getPlayerById(playerId)).thenReturn(expectedPlayer);

        TennisPlayer actualPlayer = tennisPlayerService.getPlayerById(playerId);

        assertNotNull(actualPlayer);
        assertEquals(3, actualPlayer.getId());
        assertEquals("Tim", actualPlayer.getFirstName());
        assertEquals("Burton", actualPlayer.getLastName());
    }

    @Test
    public void GetPlayerById_WhenIdNotExists_ShouldThrowNotFoundException() {

        when(mockTennisPlayerDAO.getPlayerById(15)).thenThrow(new NotFoundException("Player with id 15 not found"));

        Exception e = assertThrows(NotFoundException.class, () -> {
            TennisPlayer player = tennisPlayerService.getPlayerById(15);
        });
        assertEquals("Player with id 15 not found", e.getMessage());
    }

    private List<TennisPlayerDTO> getExpectedPlayerDTOs() {
        TennisPlayer mockPlayer1 = getMockPlayer1();
        TennisPlayer mockPlayer2 = getMockPlayer2();
        TennisPlayer mockPlayer3 = getMockPlayer3();
        TennisPlayer mockPlayer4 = getMockPlayer4();

        List<TennisPlayerDTO> expectedPlayers = Arrays.asList(
                new TennisPlayerDTO(mockPlayer2),
                new TennisPlayerDTO(mockPlayer4),
                new TennisPlayerDTO(mockPlayer3),
                new TennisPlayerDTO(mockPlayer1));
        return expectedPlayers;
    }

    private TennisPlayer getMockPlayer1() {
        TennisPlayer mockPlayer1 = new TennisPlayer(1, "John", "Doe", "J.DOE", 'M', new Country("USA.png", "USA"),
                "JohnDoe.png", new Data(200, 2000, 80, 185, 30, Arrays.asList(0, 0, 0, 0, 0)));
        return mockPlayer1;
    }

    private TennisPlayer getMockPlayer2() {
        return new TennisPlayer(2, "Mary", "Jane", "M.JAN", 'F', new Country("USA.png", "USA"),
                "MaryJane.png",
                new Data(2, 20000, 60, 165, 25, Arrays.asList(1, 1, 1, 1, 1)));
    }

    private TennisPlayer getMockPlayer3() {
        TennisPlayer mockPlayer3 = new TennisPlayer(3, "Tim", "Burton", "T.BUR", 'M', new Country("England.jpg", "ENG"),
                "TimBurton.png", new Data(33, 10000, 70, 175, 35, Arrays.asList(0, 0, 0, 0, 1)));
        return mockPlayer3;
    }

    private TennisPlayer getMockPlayer4() {
        return new TennisPlayer(4, "Alice", "Cooper", "A.COO", 'F', new Country("Swiss.png", "SUI"),
                "AliceCooper.png", new Data(8, 4000, 50, 155, 20, Arrays.asList(1, 1, 1, 1, 0)));
    }

}
