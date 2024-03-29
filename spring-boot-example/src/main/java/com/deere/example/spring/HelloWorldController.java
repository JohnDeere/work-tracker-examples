/**
 * Copyright 2022 Deere & Company
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.deere.example.spring;

import com.deere.isg.worktracker.ZombieDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloWorldController {
    protected static final String EXAMPLE_URL = "http://www.example.com";
    private final Executor mdcTaskExecutor;
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private ZombieDetector detector;

    @Autowired
    public HelloWorldController(ZombieDetector detector,
            @Qualifier("mdcTaskExecutor") Executor mdcTaskExecutor,
            RestTemplate restTemplate) {
        this.detector = detector;
        this.mdcTaskExecutor = mdcTaskExecutor;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }

    @GetMapping(value = "/ignore", produces = MediaType.TEXT_PLAIN_VALUE)
    public String ignoreLogging() {
        return "Add URL Patterns to the Configurer by setting " +
                "`excludePathPatterns` to ignore logging";
    }

    @GetMapping(value = "/cheese", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayCheese() {
        return "Cheese";
    }

    @GetMapping(value = "/executor", produces = MediaType.TEXT_PLAIN_VALUE)
    public String executesCommand() {
        mdcTaskExecutor.execute(() -> {
            ResponseEntity<String> resp = restTemplate.getForEntity(EXAMPLE_URL, String.class);
            logger.info("response is {}", resp.getBody());
        });

        return "Check your console logs";
    }

    @GetMapping("/user/{id}/role/{role}")
    @SuppressWarnings("Duplicates")
    public String userRole(@PathVariable("id") String user, @PathVariable("role") String role) {
        return user + ", " + role;
    }

    @GetMapping("/zombie")
    @SuppressWarnings("Duplicates")
    public void seeLongRunningAndZombie() {
        try {
            while (true) {
                // if thread exceeds 5 minutes, it will kill it immediately
                detector.killRunaway();
                Thread.sleep(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS));
            }
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
        }
    }

    void setLogger(Logger logger) {
        this.logger = logger;
    }

}
