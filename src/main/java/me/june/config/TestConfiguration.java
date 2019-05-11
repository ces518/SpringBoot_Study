package me.june.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-11
 * Time: 20:42
 **/
@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    public String hello() {
        return "Hello Test!";
    }
}
