package com.ja.tenisu.dao;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}