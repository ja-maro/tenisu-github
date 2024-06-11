package com.ja.tenisu.model;

import java.util.List;
import java.util.Objects;

public class Data {

    private int rank;
    private int points;
    private int weight;
    private int height;
    private int age;
    private List<Integer> last;

    public Data() {
    }

    public Data(int rank, int points, int weight, int height, int age, List<Integer> last) {
        this.rank = rank;
        this.points = points;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.last = last;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getLast() {
        return last;
    }

    public void setLast(List<Integer> last) {
        this.last = last;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, points, weight, height, age, last);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Data that = (Data) o;
        return rank == that.rank &&
                points == that.points &&
                weight == that.weight &&
                height == that.height &&
                age == that.age &&
                Objects.equals(last, that.last);
    }

    @Override
    public String toString() {
        return "Data [rank=" + rank +
                ", points=" + points +
                ", weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", last=" + last +
                ']';
    }

}
