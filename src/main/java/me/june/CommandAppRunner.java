package me.june;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-07
 * Time: 21:49
 **/
@Component
public class CommandAppRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(Arrays.toString(args));
    }
}
