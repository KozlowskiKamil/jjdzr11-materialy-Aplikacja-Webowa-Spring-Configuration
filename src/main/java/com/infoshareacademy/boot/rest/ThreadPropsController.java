package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.config.ThreadProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thread")
public class ThreadPropsController {

    private final ThreadProps props;
    private final Integer batch;
    private final Integer threads;

    public ThreadPropsController(@Autowired ThreadProps props,
                                 @Value("${app.thread.batch}") Integer batch,
                                 @Value("${app.thread.threads}") Integer threads) {
        this.props = props;
        this.batch = batch;
        this.threads = threads;
    }

    @GetMapping("/batch")
    public ResponseEntity<Integer> batch() {
        return ResponseEntity.ok(batch);
    }

    @GetMapping("/threads")
    public ResponseEntity<Integer> threads() {
        return ResponseEntity.ok(threads);
    }

    @GetMapping("/all")
    public ResponseEntity<ThreadProps> all() {
        return ResponseEntity.ok(props);
    }
}
