package me.june.mvc;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-16
 * Time: 21:34
 **/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
