package com.web.flux.mongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author mohammad Alsharif
 */


@Data
@AllArgsConstructor
@Builder
@Document
public class Movie extends ParentModel implements Serializable {

    @Id
    private final String id;
    @JsonProperty("Title")
    private final String title;
    @JsonProperty("Year")
    private final String year;
    @JsonProperty("Rated")
    private final String rated;
    @JsonProperty("Released")
    private final String released;
    @JsonProperty("Runtime")
    private final String runtime;
    @JsonProperty("Genre")
    private final String genre;
    @JsonProperty("Director")
    private final String director;
    @JsonProperty("Writer")
    private final String writer;
    @JsonProperty("Actors")
    private final String actors;
    @JsonProperty("Plot")
    private final String plot;
    @JsonProperty("Language")
    private final String language;
    @JsonProperty("Country")
    private final String country;
    @JsonProperty("Awards")
    private final String awards;
    @JsonProperty("Poster")
    private final String poster;
    @JsonProperty("Ratings")
    private final List<Rating> ratings;
    @JsonProperty("Metascore")
    private final String metaScore;
    @JsonProperty("imdbRating")
    private final String imdbRating;
    @JsonProperty("imdbVotes")
    private final String imdbVotes;
    @JsonProperty("imdbID")
    private final String imdbId;
    @JsonProperty("Type")
    private final String type;
    @JsonProperty("DVD")
    private final String dvd;
    @JsonProperty("BoxOffice")
    private final String boxOffice;
    @JsonProperty("Production")
    private final String production;
    @JsonProperty("Website")
    private final String website;
    @JsonProperty("Response")
    private final String response;
}
