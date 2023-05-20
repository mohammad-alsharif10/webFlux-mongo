package com.web.flux.mongo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

/**
 * @author mohammad Alsharif
 */

@Builder
public record Rating(@JsonProperty("Source") String source,
                     @JsonProperty("Value") String value) implements Serializable {

}
