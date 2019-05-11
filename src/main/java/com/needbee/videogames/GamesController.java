package com.needbee.videogames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/games")
public class GamesController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping(path="")
    public @ResponseBody Iterable<Game> index() {
        return gameRepository.findAll();
    }

    // TODO make it a POST instead
    @GetMapping(path="/create")
    public ResponseEntity<Game> create(@RequestParam String name, @RequestParam Integer releaseYear) {
        Game g = new Game();
        g.setName(name);
        g.setReleaseYear(releaseYear);
        gameRepository.save(g);
        return new ResponseEntity<>(g, HttpStatus.CREATED);
    }
}
