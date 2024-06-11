package com.ja.tenisu.model;

public class TennisPlayerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String shortName;
    private char sex;
    private int rank;
    private Country country;
    private String picture;

    public TennisPlayerDTO(int id, String firstName, String lastName, String shortName, char sex, Country country,
            String picture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortName = shortName;
        this.sex = sex;
        this.country = country;
        this.picture = picture;
    }

    public TennisPlayerDTO(TennisPlayer tennisPlayer) {
        this.id = tennisPlayer.getId();
        this.firstName = tennisPlayer.getFirstName();
        this.lastName = tennisPlayer.getLastName();
        this.shortName = tennisPlayer.getShortName();
        this.sex = tennisPlayer.getSex();
        this.rank = tennisPlayer.getRank();
        this.country = tennisPlayer.getCountry();
        this.picture = tennisPlayer.getPicture();
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

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

}
