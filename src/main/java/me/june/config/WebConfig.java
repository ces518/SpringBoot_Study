package me.june.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-19
 * Time: 21:32
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 스프링부트가 제공하는 기본 리소스 핸들러를 유지하면서
     * 추가설정
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")
                .addResourceLocations("classpath:/m/") // 반드시 / 로 끝나야 매핑이된다.
                .setCachePeriod(30); // 단위는 초단위
    }

    /* CORS Global Setting */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/hello").allowedOrigins("http://localhost:18080");
    }
}
