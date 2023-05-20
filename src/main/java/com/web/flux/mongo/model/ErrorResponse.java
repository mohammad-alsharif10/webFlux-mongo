package com.web.flux.mongo.model;

import java.io.Serializable;

/**
 * @author mohammad Alsharif
 */


public record ErrorResponse(Integer status, String message) implements Serializable {

}
