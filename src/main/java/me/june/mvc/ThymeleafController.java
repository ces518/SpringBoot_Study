package me.june.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-21
 * Time: 20:12
 **/
@Controller
public class ThymeleafController {

    @GetMapping("/helloThymeleaf")
    public String helloThymeLeaf(Model model) {
        model.addAttribute("name","june");
        return "helloThymeleaf";
    }
}
