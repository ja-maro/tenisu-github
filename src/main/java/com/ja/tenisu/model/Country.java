package com.ja.tenisu.model;

import java.util.Objects;

public class Country {

    private String picture;
    private String code;

    public Country() {
    }

    public Country(String picture, String code) {
        this.picture = picture;
        this.code = code;
    }

    public String getPicture() {
        return picture;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(picture, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Country that = (Country) o;
        return Objects.equals(picture, that.picture) &&
                Objects.equals(code, that.code);
    }

    @Override
    public String toString() {
        return "Country [picture=" + picture +
                ", code=" + code +
                ']';
    }

}
