package com.example.videogames;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {
}
