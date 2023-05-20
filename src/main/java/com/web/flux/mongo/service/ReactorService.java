package com.web.flux.mongo.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

/**
 * @author mohammad Alsharif
 */
@Service
@Slf4j
@Getter
public class ReactorService {

    private Sinks.Many<String> behaviorSubject;

    public ReactorService() {
        behaviorSubject = Sinks
                .many() // multiple values are expected to be emitted
                .replay() // replay value(s) for late subscribers
                .latestOrDefault("hello");
        behaviorSubject.asFlux().subscribe(value -> log.info("value from subject {}", value));
    }


}
