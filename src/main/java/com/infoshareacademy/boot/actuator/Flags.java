package com.infoshareacademy.boot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "flags")
public class Flags {
    private Map<String, Boolean> flags = init();

    @ReadOperation
    public Map<String, Boolean> flags() {
        return flags;
    }

    @WriteOperation
    public void setFlag(@Selector String name, Boolean enabled) {
        flags.put(name, enabled);
    }

    @DeleteOperation
    public void removeFlag(@Selector String name) {
        flags.remove(name);
    }

    private Map<String, Boolean> init() {
        Map<String, Boolean> flags = new ConcurrentHashMap<>();
        flags.put("feature1", false);
        flags.put("feature2", false);
        return flags;
    }
}
