package me.june.mvc;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-16
 * Time: 21:34
 **/

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/users/create")
    @ResponseBody
    public User create (@RequestBody User user) {
        return user;
    }

    /**
     * GET 방식으로 넘겼을때 setter가 존재하지않고 , 기본생성자만 있을경우 바인딩이 되는지 확인.
     * @param user
     * @return
     */
    @GetMapping("/users/create")
    @ResponseBody
    public User createGet (@RequestBody User user) {
        return user;
    }
}
