package com.infoshareacademy.boot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "settings")
public class Settings {
    private Map<String, String> settings = init();

    @ReadOperation
    public Map<String, String> settings() {
        return settings;
    }

    @WriteOperation
    public void setFlag(@Selector String name, String setting) {
        settings.put(name, setting);
    }

    @DeleteOperation
    public void removeFlag(@Selector String name) {
        settings.remove(name);
    }

    private Map<String, String> init() {
        Map<String, String> settings = new ConcurrentHashMap<>();
        settings.put("setting1", "raz,dwa,trzy");
        settings.put("setting2", "hello");
        return settings;
    }
}
