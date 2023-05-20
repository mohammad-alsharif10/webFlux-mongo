package com.web.flux.mongo.controller;

import com.web.flux.mongo.service.ReactorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mohammad Alsharif
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reactorController")
public class ReactorController {
    private final ReactorService reactorService;

    @GetMapping("/fireEvent")
    public String fireEvent() {
        this.reactorService.getBehaviorSubject().tryEmitNext("hello from the other side");
        return "done";
    }
}
