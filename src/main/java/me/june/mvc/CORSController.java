package me.june.mvc;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-25
 * Time: 21:49
 **/
@RestController
public class CORSController {

    /* 허용할 오리진 설정 */
    @CrossOrigin(origins = "http://localhost:18080")
    @GetMapping("/helloCors")
    public String helloCors () {
        return "helloCors";
    }
}
