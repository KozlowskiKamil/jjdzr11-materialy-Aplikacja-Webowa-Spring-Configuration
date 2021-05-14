package com.infoshareacademy.boot.rest;

import com.infoshareacademy.boot.config.AppProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
public class PropertiesController {

    private final AppProps appProps;

    @Value("${app.author}")
    private String author;

    @Value("${app.name}")
    private String name;

    public PropertiesController(AppProps appProps) {
        this.appProps = appProps;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/author")
    public ResponseEntity<String> author() {
        return ResponseEntity.ok(author);
    }

    @GetMapping("/name")
    public ResponseEntity<String> name() {
        return ResponseEntity.ok(name);
    }

    @GetMapping("/all")
    public ResponseEntity<AppProps> all() {
        return ResponseEntity.ok(appProps);
    }
}
