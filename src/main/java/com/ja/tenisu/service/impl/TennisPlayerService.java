package com.ja.tenisu.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.tenisu.dao.ITennisPlayerDAO;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayerDTO;
import com.ja.tenisu.service.ITennisPlayerService;

@Service
public class TennisPlayerService implements ITennisPlayerService {

    private static final Logger log = LoggerFactory.getLogger(TennisPlayerService.class);

    @Autowired
    ITennisPlayerDAO tennisPlayerDAO;

    @Override
    public List<TennisPlayerDTO> getAllPlayersWithoutDataByRank() {
        try {
            return tennisPlayerDAO.getAllPlayersWithoutDataByRank();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public TennisPlayer getPlayerById(int id) {
        try {
            return tennisPlayerDAO.getPlayerById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}
