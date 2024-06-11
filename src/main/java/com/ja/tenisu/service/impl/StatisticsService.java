package com.ja.tenisu.service.impl;

import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.tenisu.dao.ITennisPlayerDAO;
import com.ja.tenisu.model.Country;
import com.ja.tenisu.model.HeightWeight;
import com.ja.tenisu.model.TennisPlayer;
import com.ja.tenisu.model.TennisPlayersStatistics;
import com.ja.tenisu.service.IStatisticsService;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    ITennisPlayerDAO tennisPlayerDAO;

    @Override
    public TennisPlayersStatistics getTennisPlayersStatistics() {
        String averageBMI = formatDoubleToStringWith2Decimals(getPlayersAverageBMI());
        String medianHeight = formatDoubleToStringWith2Decimals(getPlayersMedianHeight());
        return new TennisPlayersStatistics(
                getHighestWinRatioCountry(),
                averageBMI,
                medianHeight);
    }

    @Override
    public Country getHighestWinRatioCountry() {
        List<TennisPlayer> players = tennisPlayerDAO.getAllPlayers();
        HashMap<Country, List<Double>> countriesRatiosPerPlayer = getCountriesRatiosPerPlayer(players);
        HashMap<Country, Double> countriesRatios = getCountriesRatios(countriesRatiosPerPlayer);
        Country highestWinRatioCountry = countriesRatios.entrySet().stream()
                .max((ratio1, ratio2) -> ratio1.getValue().compareTo(ratio2.getValue())).get().getKey();

        return highestWinRatioCountry;
    }

    private HashMap<Country, Double> getCountriesRatios(HashMap<Country, List<Double>> countriesRatiosPerPlayer) {
        HashMap<Country, Double> countriesRatios = new HashMap<>();
        for (Country country : countriesRatiosPerPlayer.keySet()) {
            double averageRatio = countriesRatiosPerPlayer.get(country).stream()
                    .mapToDouble(Double::doubleValue)
                    .average().orElse(0.0);
            countriesRatios.put(country, averageRatio);
        }
        return countriesRatios;
    }

    private HashMap<Country, List<Double>> getCountriesRatiosPerPlayer(List<TennisPlayer> players) {
        HashMap<Country, List<Double>> countriesRatiosPerPlayer = new HashMap<>();
        for (TennisPlayer player : players) {
            countriesRatiosPerPlayer.computeIfAbsent(player.getCountry(), key -> new ArrayList<>())
                    .add(getWinRatio(player));
        }
        return countriesRatiosPerPlayer;
    }

    private double getWinRatio(TennisPlayer player) {
        List<Integer> lastScores = player.getLast();
        double ratio = lastScores.stream()
                .mapToDouble(score -> score.doubleValue())
                .average()
                .orElse(0.0);
        return ratio;
    }

    @Override
    public Double getPlayersAverageBMI() {
        List<HeightWeight> heightAndWeights = tennisPlayerDAO.getAllHeightsAndWeights();

        double averageBMI = heightAndWeights.stream()
                .mapToDouble(heightWeight -> getBMI(heightWeight))
                .average()
                .orElse(0.0);
        return averageBMI;
    }

    private String formatDoubleToStringWith2Decimals(double averageBMI) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return df.format(averageBMI);
    }

    @Override
    public Double getPlayersMedianHeight() {
        List<Double> sortedHeights = tennisPlayerDAO.getAllHeights().stream().sorted()
                .map(height -> height.doubleValue())
                .collect(Collectors.toList());
        int size = sortedHeights.size();
        double medianHeight = (size % 2 == 0 ? (sortedHeights.get(size / 2 - 1) + sortedHeights.get(size / 2)) / 2
                : sortedHeights.get(size / 2));
        return medianHeight;
    }

    private Double getBMI(HeightWeight heightWeight) {
        double weight = (double) heightWeight.getWeight() / 1000d;
        double height = (double) heightWeight.getHeight() / 100d;
        return Double.valueOf(weight / (height * height));
    }

}
