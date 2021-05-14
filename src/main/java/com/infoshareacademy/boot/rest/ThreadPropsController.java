package com.infoshareacademy.boot.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thread")
public class ThreadPropsController {

    // TODO

    public ThreadPropsController() {
        // TODO
    }

    @GetMapping("/batch")
    public ResponseEntity<Integer> batch() {
        return ResponseEntity.ok(1);
    }

    @GetMapping("/threads")
    public ResponseEntity<Integer> threads() {
        return ResponseEntity.ok(2);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        return ResponseEntity.ok("props");
    }
}
