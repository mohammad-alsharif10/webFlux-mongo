package com.web.flux.mongo.repository;

import com.web.flux.mongo.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author mohammad Alsharif
 */
public interface ReactiveMovieRepository extends ReactiveMongoRepository<Movie, String> {
}
