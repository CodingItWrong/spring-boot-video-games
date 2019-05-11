package com.needbee.videogames;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Integer id) {
        super("Could not find game " + id);
    }
}
