package com.example.videogames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository repository;

    @GetMapping("")
    Iterable<Game> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Game show(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Game create(@RequestBody Game newGame) {
        return repository.save(newGame);
    }

    @PatchMapping("/{id}")
    Game update(@RequestBody Game newGame, @PathVariable Integer id) {
        return repository.findById(id)
                .map(game -> {
                    game.setName(newGame.getName());
                    newGame.setReleaseYear(newGame.getReleaseYear());
                    return repository.save(game);
                })
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
