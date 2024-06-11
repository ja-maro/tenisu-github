package com.ja.tenisu.model;

public class TennisPlayersStatistics {

    private Country highestWinRatioCountry;
    private String averageBMI;
    private String medianHeight;

    public TennisPlayersStatistics(Country highestWinRatioCountry, String averageBMI, String medianHeight) {
        this.highestWinRatioCountry = highestWinRatioCountry;
        this.averageBMI = averageBMI;
        this.medianHeight = medianHeight;
    }

    public Country getHighestWinRatioCountry() {
        return highestWinRatioCountry;
    }

    public void setHighestWinRatioCountry(Country highestWinRatioCountry) {
        this.highestWinRatioCountry = highestWinRatioCountry;
    }

    public String getAverageBMI() {
        return averageBMI;
    }

    public void setAverageBMI(String averageBMI) {
        this.averageBMI = averageBMI;
    }

    public String getMedianHeight() {
        return medianHeight;
    }

    public void setMedianHeight(String medianHeight) {
        this.medianHeight = medianHeight;
    }

}
