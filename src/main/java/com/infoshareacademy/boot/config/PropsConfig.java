package com.infoshareacademy.boot.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        AppProps.class,
        ThreadProps.class
})
public class PropsConfig {
}
