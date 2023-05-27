package com.web.flux.mongo.service;

import com.web.flux.mongo.exception.RecordNotFoundException;
import com.web.flux.mongo.model.Movie;
import com.web.flux.mongo.model.Rating;
import com.web.flux.mongo.repository.ReactiveMovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 * @author mohammad Alsharif
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MovieService {

    private final ReactiveMovieRepository reactiveMovieRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Movie> saveMovie(Movie movie) {
        return reactiveMovieRepository.save(movie);
    }

    public Flux<Movie> saveMovies(List<Movie> movies) {
        return reactiveMongoTemplate.insertAll(Mono.just(movies), Movie.class).doOnError(throwable -> log.error("error inserting {}",throwable.getMessage()));
    }

    public Mono<Movie> findById(String id) {
        return reactiveMovieRepository.findById(id);
    }

    public Flux<Movie> findAll() {
        return reactiveMovieRepository.findAll();
    }

    public Mono<Movie> update(String id, Movie movie) {
        return this.reactiveMovieRepository
                .findById(id)
                .filter(Objects::nonNull)
                .flatMap(dbMovie -> {
                    dbMovie = movie;
                    return this.reactiveMovieRepository.save(dbMovie);
                }).switchIfEmpty(Mono.error(new RecordNotFoundException(MessageFormat.format(" record with id -> {0} is not found", id))));
    }


    public Flux<Rating> getMovieRatings(String id) {
        return this.reactiveMovieRepository
                .findById(id)
                .flatMapIterable(Movie::getRatings);
    }


}
