package me.june;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-14
 * Time: 21:01
 **/
@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/hello")
    public String hello() {
        return "hello" + sampleService.getName();
    }
}
