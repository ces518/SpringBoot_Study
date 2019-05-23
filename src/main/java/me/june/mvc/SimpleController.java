package me.june.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-23
 * Time: 20:38
 **/
@RestController
public class SimpleController {

    @GetMapping("/handle")
    public String handler () {
        throw new SimpleException();
    }

    @ExceptionHandler(SimpleException.class)
    public ResponseEntity simpleHandler () {
        AppError appError = new AppError();
        appError.setMessage("error");
        appError.setReason("IDK");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appError);
    }

}
