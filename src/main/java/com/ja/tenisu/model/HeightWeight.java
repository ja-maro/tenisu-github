package com.ja.tenisu.model;

import java.util.Objects;

public class HeightWeight {

    private int height;
    private int weight;

    public HeightWeight(TennisPlayer tennisPlayer) {
        this.height = tennisPlayer.getHeight();
        this.weight = tennisPlayer.getWeight();
    }

    public HeightWeight(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "HeightWeight [height=" + height + ", weight=" + weight + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HeightWeight other = (HeightWeight) obj;
        return height == other.height && weight == other.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight);
    }

}
