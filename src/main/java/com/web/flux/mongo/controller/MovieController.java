package com.web.flux.mongo.controller;

import com.web.flux.mongo.model.Movie;
import com.web.flux.mongo.model.Rating;
import com.web.flux.mongo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author mohammad Alsharif
 */
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/saveMovie")
    public Mono<Movie> saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PostMapping("/saveMovies")
    public Flux<Movie> saveMovies(@RequestBody List<Movie> movies) {
        return movieService.saveMovies(movies);
    }

    @GetMapping("/findById/{id}")
    public Mono<Movie> findById(@PathVariable String id) {
        return movieService.findById(id);
    }

    @GetMapping("/findAll")
    public Flux<Movie> findAll() {
        return movieService.findAll();
    }


    @PutMapping("/update/{id}")
    public Mono<Movie> update(@PathVariable String id,@RequestBody Movie movie) {
        return movieService.update(id,movie);
    }

    @GetMapping("/getMovieRatings/{id}")
    public Flux<Rating> findAll(@PathVariable String id) {
        return movieService.getMovieRatings(id);
    }

}
