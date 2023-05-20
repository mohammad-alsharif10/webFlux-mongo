package com.web.flux.mongo.exception;

/**
 * @author mohammad Alsharif
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
