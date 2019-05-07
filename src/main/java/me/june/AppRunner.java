package me.june;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-03
 * Time: 23:20
 **/
@Component
@Order(1)
public class AppRunner implements ApplicationRunner {

    @Autowired
    HoloMan holoMan;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoMan);
    }
}
