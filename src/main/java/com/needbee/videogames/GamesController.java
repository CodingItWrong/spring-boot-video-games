package com.needbee.videogames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GamesController {
    @Autowired
    private GameRepository repository;

    @GetMapping("")
    Iterable<Game> index() {
        return repository.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Game create(@RequestBody Game newGame) {
        return repository.save(newGame);
    }

    @GetMapping("/{id}")
    Game show(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    @PatchMapping("/{id}")
    Game update(@RequestBody Game newGame, @PathVariable Integer id) {
        return repository.findById(id)
                .map(game -> {
                    game.setName(newGame.getName());
                    game.setReleaseYear(newGame.getReleaseYear());
                    return repository.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);
                    return repository.save(newGame);
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
