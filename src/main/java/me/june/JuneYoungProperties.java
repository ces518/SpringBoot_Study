package me.june;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-09
 * Time: 22:00
 **/
@Component
@ConfigurationProperties("me.june")
@Validated
public class JuneYoungProperties {

    private String name;

    private int age;

    private String fullName;

//    @DurationUnit(ChronoUnit.SECONDS)
    private Duration secound = Duration.ofSeconds(30);

    public Duration getSecound() {
        return secound;
    }

    public void setSecound(Duration secound) {
        this.secound = secound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
