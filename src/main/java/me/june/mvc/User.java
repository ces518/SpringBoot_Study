package me.june.mvc;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-17
 * Time: 22:09
 **/
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
