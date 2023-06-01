package com.web.flux.mongo.service;

import com.web.flux.mongo.exception.BulkInsertException;
import com.web.flux.mongo.exception.RecordNotFoundException;
import com.web.flux.mongo.model.Movie;
import com.web.flux.mongo.model.Rating;
import com.web.flux.mongo.repository.ReactiveMovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author mohammad Alsharif
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MovieService {

    private final ReactiveMovieRepository reactiveMovieRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final StopWatch stopWatch;

    public Mono<Movie> saveMovie(Movie movie) {
        return reactiveMovieRepository.save(movie);
    }

    public Flux<Movie> saveMovies(List<Movie> movies) {
        return reactiveMongoTemplate
                .insertAll(Mono.just(movies), Movie.class)
                .doOnError(throwable -> {
                    throw new BulkInsertException(throwable.getLocalizedMessage());
                });
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
                .switchIfEmpty(Mono.error(new RecordNotFoundException(MessageFormat.format(" record with id -> {0} is not found", id))))
                .flatMap(dbMovie -> {
                    dbMovie = movie;
                    return this.reactiveMovieRepository.save(dbMovie);
                });
    }


    public Flux<Rating> getMovieRatings(String id) {
        return this.reactiveMovieRepository
                .findById(id)
                .flatMapIterable(Movie::getRatings);
    }

    public Mono<Void> directDelete(String id) {
        this.stopWatch.start();
        Mono<Void> deleteById = this.reactiveMovieRepository.deleteById(id);
        this.stopWatch.stop();
        log.info("time for directDelete -> {}", stopWatch.getTotalTimeMillis());
        return deleteById;
    }

    public Mono<Void> findAndDelete(String id) {
        this.stopWatch.start();
        Mono<Void> findAndDelete = this.reactiveMovieRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RecordNotFoundException(MessageFormat.format(" record with id -> {0} is not found", id))))
                .flatMap(movie -> this.reactiveMovieRepository.deleteById(id));
        this.stopWatch.stop();
        log.info("time for findAndDelete  -> {}", stopWatch.getTotalTimeMillis());
        return findAndDelete;
    }

    public Flux<Movie> findAndDeleteMongoTemplate(String id) {
        this.stopWatch.start();
        Flux<Movie> allAndRemove = this.reactiveMongoTemplate.findAllAndRemove(new Query(where("id").is(id)), Movie.class);
        this.stopWatch.stop();
        log.info("time for  findAndDeleteMongoTemplate -> {}", stopWatch.getTotalTimeMillis());
        return allAndRemove;
    }


}
