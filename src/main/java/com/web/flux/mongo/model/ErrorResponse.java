package com.web.flux.mongo.model;

import java.io.Serializable;

/**
 * @author mohammad Alsharif
 */


public record ResponseWrapper<T extends ParentModel>(Integer status, String message) implements Serializable {

}
