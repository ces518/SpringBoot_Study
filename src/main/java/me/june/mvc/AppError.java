package me.june.mvc;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-23
 * Time: 20:42
 **/
public class AppError {
    private String message;
    private String reason;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
