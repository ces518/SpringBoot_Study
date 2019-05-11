package me.june;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-07
 * Time: 21:28
 **/

/**
 * 애플리케이션 리스너 등록
 *
 */
@Component
//public class SimpleListener implements ApplicationListener<ApplicationStartingEvent> {
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${me.june.name}")
    private String name;

    @Value("${me.june.fullName}")
    private String fullName;

    //ConfigurationProperties 활용한 출력
    @Autowired
    private JuneYoungProperties juneYoungProperties;

    @Autowired
    private String hello;

    @Override
//    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println("app is started !!");
        System.out.println(String.format("name = {%s}", name));
        System.out.println(String.format("fullName = {%s}", fullName));

        System.out.println("================= using ConfigurationProperties =================");
        System.out.println(String.format("name = {%s}", juneYoungProperties.getName()));
        System.out.println(String.format("fullName = {%s}", juneYoungProperties.getFullName()));
        System.out.println(String.format("seound = {%s}",juneYoungProperties.getSecound()));

        System.out.println("===================== profile Test =====================");
        System.out.println(hello);
    }
}
