package com.ja.tenisu.service;

import com.ja.tenisu.model.Country;
import com.ja.tenisu.model.TennisPlayersStatistics;

public interface IStatisticsService {

    public TennisPlayersStatistics getTennisPlayersStatistics();

    public Country getHighestWinRatioCountry();

    public Double getPlayersAverageBMI();

    public Double getPlayersMedianHeight();

}
