package com.web.flux.mongo.exception;


import com.web.flux.mongo.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import static com.web.flux.mongo.util.Constants.RECORD_NOT_FOUND_EXCEPTION;

/**
 * @author mohammad Alsharif
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(RecordNotFoundException.class)
    public Mono<ErrorResponse> recordNotFound(RecordNotFoundException recordNotFoundException) {
        log.error(RECORD_NOT_FOUND_EXCEPTION, recordNotFoundException);
        return Mono.just(new ErrorResponse(HttpStatus.NOT_FOUND.value(), recordNotFoundException.getMessage()));
    }

}
