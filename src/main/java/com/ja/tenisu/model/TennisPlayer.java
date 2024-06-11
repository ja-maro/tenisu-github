package com.ja.tenisu.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TennisPlayer {

    private int id;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("shortname")
    private String shortName;
    private char sex;
    private Country country;
    private String picture;
    private Data data;

    public TennisPlayer() {
    }

    public TennisPlayer(int id, String firstName, String lastName, String shortName, char sex, Country country,
            String picture, Data data) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortName = shortName;
        this.country = country;
        this.picture = picture;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public char getSex() {
        return sex;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @JsonIgnore
    public int getRank() {
        return this.data.getRank();
    }

    public void setRank(int rank) {
        this.data.setRank(rank);
    }

    @JsonIgnore
    public int getPoints() {
        return data.getPoints();
    }

    public void setPoints(int points) {
        this.data.setPoints(points);
    }

    @JsonIgnore
    public int getWeight() {
        return this.data.getWeight();
    }

    public void setWeight(int weight) {
        this.data.setWeight(weight);
    }

    @JsonIgnore
    public int getHeight() {
        return data.getHeight();
    }

    public void setHeight(int height) {
        this.data.setHeight(height);
    }

    @JsonIgnore
    public int getAge() {
        return this.data.getAge();
    }

    public void setAge(int age) {
        this.data.setAge(age);
    }

    @JsonIgnore
    public List<Integer> getLast() {
        return this.data.getLast();
    }

    public void setLast(List<Integer> last) {
        this.data.setLast(last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, shortName, sex, country, picture, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TennisPlayer that = (TennisPlayer) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(shortName, that.shortName) &&
                sex == that.sex &&
                Objects.equals(country, that.country) &&
                Objects.equals(picture, that.picture) &&
                Objects.equals(data, that.data);
    }

    @Override
    public String toString() {
        return "TennisPlayer [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", shortName=" + shortName +
                ", sex=" + sex +
                ", country=" + country +
                ", picture=" + picture +
                ", data=" + data +
                ']';
    }

}
