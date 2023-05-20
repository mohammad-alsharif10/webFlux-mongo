package com.web.flux.mongo.exception;


import com.web.flux.mongo.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author mohammad Alsharif
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    
    public Mono<ErrorResponse> recordNotFound(){

    }

}
