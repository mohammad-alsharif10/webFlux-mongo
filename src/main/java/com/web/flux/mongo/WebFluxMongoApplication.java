package com.web.flux.mongo;

import com.web.flux.mongo.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static reactor.core.publisher.Flux.just;

/**
 * @author Mohammad Alsharif
 */
@SpringBootApplication
@Slf4j
public class WebFluxMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxMongoApplication.class, args);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventListener() {
        return event -> {
            var rating = new Rating(" hi ", " hi ");
            var build = Rating.builder().build();
            log.info("Hello WebFlux");
            log.info("rating record {}", rating);
            log.info("rating builder {}", build);
            just('S', 'p', 'a', 'r', 't', 'a')
                    .map(Character::toLowerCase)
                    .flatMap(this::toMorseCode);
            just(1, 2, 3).delayElements(Duration.ofMillis(1));
        };
    }

    Flux<String> toMorseCode(char ch) {
        return switch (ch) {
            case 'a' -> just("DI", "DAH");
            case 'b' -> just("DxI", "DAxH");
            case 'c' -> just("DIcc", "DxAH");
            default -> Flux.empty();
        };
    }

}
