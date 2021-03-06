package me.june;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-01
 * Time: 19:09
 **/
@SpringBootApplication
//@SpringBootConfiguration
//@ComponentScan
//@EnableAutoConfiguration
public class Applicaiton {

    public static void main(String[] args) {
//        SpringApplication application = new SpringApplication(Applicaiton.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.run(args);
        SpringApplication application = new SpringApplication(Applicaiton.class);
        //배너를 끄고싶은 경우
        application.setBannerMode(Banner.Mode.OFF);
//        application.addListeners(new SimpleListener());
        application.run(args);
//        new SpringApplicationBuilder()
//                    .sources(Applicaiton.class)
//                    .run(args);
//        // 톰캣 객체생성
//        Tomcat tomcat = new Tomcat();
//        // 톰캣 포트설정
//        tomcat.setPort(8080);
//        // 컨텍스트 설정
//        Context context = tomcat.addContext("/", "/");
//
//        HttpServlet servlet = new HttpServlet() {
//            @Override
//            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.println("<html><head><title>HelloTomcat</title></head>");
//                writer.println("<body>Hello !!! </body></html>");
//            }
//        };
//
//
//        // 톰캣에 서블릿 추가
//        tomcat.addServlet("/","hello",servlet);
//        // 컨텍스트에 서블릿매핑 추
//        context.addServletMappingDecoded("/hello","hello");
//
//        // 톰캣실행
//        tomcat.start();
//
//        tomcat.getServer().await();


    }

}
