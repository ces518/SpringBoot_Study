# Springboot 

- 스프링 부트란 ?
    - 프로덕션 레벨의 애플리케이션을 좀 더 쉽고 빠르게 개발이 가능하도록  폭 넓은 개발환경을 제공해준다.
    - 컨벤션으로 정해져있는 기본적으로 널리 사용하는 설정들을 제공해준다.
    - 원한다면 개발자 마음대로 커스터마이징도 가능하다.
    


# Spring boot 시작하기
    - IDE에서는 바로 스프링부트 프로젝트 생성을 제공하지만
    - 우선 maven project로 생성해보자.
    - 아래의 의존성들과 플러그인을 추가해준다.
```xml
<!--    스프링 부트의 가장 핵심적인 의존성
        MAVEN을 활용하여 계층구조로 생성이 가능하다.
-->

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.4.RELEASE</version>
    </parent>

<!--   스프링부트 웹 의존성  -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

<!--   스프링부트 메이븐 플러그인  -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

```
spring-boot-parent 는 의존성관리에 있어서 가장 핵심적인 부분이다.
web 은 스프링부트를 웹 프로젝트로 개발이 가능하도록 모아둔 의존성이다.
plugin은 스프링부트를 사용가능하도록 해주는 메이븐 플러그인부분이다.

- 기본적인 세팅은 종료

그리고 난 뒤 , 스프링 부트를 실행할 수 있는 메인 클래스를 생성한다.

```java
@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args) {
        SpringApplication.run(Applicaiton.class,args);
    }
}
```

@SpringBootApplication 애노테이션을 사용해서 메인 클래스라는것을 명시해주고
SpringApplication.run('클래스',args); 실행을하면 스프링부트 애플리케이션이 정상적으로 실행되는것을 확인할 수 있다.


# Spring boot 프로젝트 구조
    
메이븐 프로젝트 구조와 동일하다.

src/main/java : 소스코드
src/main/resource : 소스 리소스 (classpath: 키워드를 사용하여 접근할 수 있다.)
src/test/java : 테스트 코드
src/test/resource : 테스트 리소스

* 스프링부트에서 추천하는 메인클래스 경로
    - 프로젝트에서 사용하는 가장 최상위 패키지에 두는것을 권장한다.
    
* 이유 
    - 해당 클래스가 컴포넌트 스캔을 하는데 해당 패키지부터 하위패키지 탐색을 진행한다.
    - 가장 기본 패키지를 생성하고 해당 패키지에 메인 애플리케이션 클래스를 두는것을 권장한다.
    
   
# Spring boot 원리 - 의존성 관리 이해
- 스프링부트는 의존성관리를 제공하기때문에 버전을 명시하지않아도 
- 스프링부트에서 의존성을 관리하는 의존성의 경우 스프링부트가 권장하는 버전의 의존성을 가져온다.
- springboot-parent < springboot-dependencies

- 장점 
    - 스프링부트가 의존성을 관리해주기 때문에 관리해야할 의존성의 수가 줄어든다.
        
- 프로젝트의 parent가 지정되어있어서 스프링부트 parent를 사용하지 못하는경우 ?
    - 1. 프로젝트의 parent의 parent로 스프링부트 parent를 지정해준다.
    - 2. 프로젝트의 parent로 지정해주지 못할경우 dependencyManagement 엘리먼트를 사용하여 의존성 관리를 받는다.
        - 이 방법의 경우 parent는 의존성 관리외에도 각종 프로퍼티등 설정을 지원해주는데 그런 부분들을 수동으로 해주어야함.
        
        
# Spring boot 원리 - 의존성 관리 응용
- spring boot starter 프로젝트를 의존성으로 추가하면 의존성 관리를 스프링부트가 제공하기때문에
- 버전을 명시하지않아도 된다.
- 스프링부트가 의존성을 관리해주지않는 의존성일경우 버전을 명시해주는것이 베스트프렉티스이다.
```xml
<!--    스프링부트 starter jpa -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

- 스프링부트가 관리하는 의존성의 버전을 바꾸고싶은경우 ? 
    - parent에 존재하는 엘리먼트 다음과 같이 프로퍼티 엘리먼트에 오버라이딩 해주면된다.
```xml
<properties>
    <spring.version>5.0.7.RELEASE</spring.version>
</properties>
```

# Spring boot 원리 - 자동 설정

- @EnableAutoConfiguration (@SpringBootApplication에 포함)
- 스프링부트는 빈을 2 단계에 걸쳐서 등록을 다.
    - 1. ComponentScan
    - 2. EnableAutoConfiguration

1. ComponentScan
    - @Component 애노테이션으로 등록된 객체들을 빈으로 등록한다.
    - @Controller , @Service ... 등

2. EnableAutoConfiguration
    - spring.factories 
        - org.springframework.boot.autoconfigure.EnableAutoConfiguration 하위에 
        - autoConfiguration 컨벤션들이 존재한다.
        - 해당 설정들을 모두 읽어들인다.
        - @Conditional~ 로 시작하는 애노테이션 > 조건에 따라 설정을 제공한다.

- @SpringBootApplication은 사실상 아래의 3가지 애노테이션과 동일하다.    
- SpringBootApplication을 static 메서드가아닌 인스턴스를 생성해서 사용할경우 다양한 커스터마이징이 가능하다.
- 다음과 같이 WebApplicationType을 NONE으로 설정할경우 , WebApplication이 아닌 애플리케이션으로 실행이 된다.
```java
//@SpringBootApplication
@SpringBootConfiguration
@ComponentScan
@EnableAutoConfiguration
public class Applicaiton {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Applicaiton.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
//        SpringApplication.run(Applicaiton.class,args);
    }
}
```
    
    
    
# Spring boot 원리 - Custom AutoConfiguration
- Xxx-Spring-Boot-AutoConfigure : 자동설정
- Xxx-Spring-Boot-Starter : 필요한 의존성 정의

- 하나로 만들고 싶은경우 ? 
    - Xxx-Spring-Boot-Starter

구현방법
    - 의존성추가
        
```xml
<!--    자동설정 을 위한 autoconfigure , autoconfigure processor 의존성 추가 -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure-processor</artifactId>
        </dependency>
    </dependencies>

<!-- dependencyManagement 설정 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.0.4.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```        
        
    - @Configuration 클래스 작성
```java
@Configuration
public class HolomanConfiguration {

    @Bean
    public HoloMan holoMan() {
        HoloMan holoMan = new HoloMan();
        holoMan.setHowLong(100);
        holoMan.setName("june");
        return holoMan;
    }
}
```    
    - src/main/resources/META-INF/spring.factories 작성
        - spring.factories 파일은 스프링부트에 한정적인 것이아닌 스프링 라이프사이클에 포함되는 파일 (serviceProvider 같은 패턴)
```properties
    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
       me.june.HolomanConfiguration

```
    - mvn install (다른 프로젝트에서 사용할 수 있도록 빌드하여 jar로 생성한다. [로컬메이븐 저장소로 설치가됨.])
    - 다음으로 사용할 프로젝트의 의존성으로 추가해준다.
```xml
<!--   커스텀한 자동설정 -->
<dependency>
    <groupId>me.june</groupId>
    <artifactId>june-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```    
    - 자동설정으로 인해 빈이 등록되었는지 확인
```java
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    HoloMan holoMan;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoMan);
        //HoloMan{name='june', howLong=100}
    }
}
```
    - 현재 프로젝트에서 새롭게 빈을 등록
    - AppRunner 를 통해 나오는 기대값은 ?  HoloMan{name=null, howLong=0}
    - 하지만 실제 출력값은 HoloMan{name='june', howLong=100}
    - 이유는 앞서 설명한 Spring-boot 의 configure 사이클때문 ..
    - ComponentScan을 통해 현재 프로젝트에서 새로이 등록한 holoman을 등록후 , AutoConfiguration을 진행하기때문에 autoConfigure프로젝트에 등록된 빈이 덮어써버리는것이다. 
```java
@Bean
public HoloMan holoMan() {
    return new HoloMan();
}
```


# Spring boot 원리 - 커스텀 자동 설정
    - 덮어쓰기 방지하기
        - 자동설정 프로젝트에 @ConditionalOnMissingBean 애노테이션 사용
        - 해당 타입의 빈이 없을 경우에만 빈을 등록하도록 설정 (ComponentScan으로 등록되는 빈을 우선)
        - 스프링 부트의 자동설정을 커스터마이징 하는 방법중 하나.
```java
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    HoloMan holoMan;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoMan);
        //HoloMan{name='null', howLong=0}
    }
}
```

    - 매번 새롭게 정의하는것이 번거롭기 때문에 properties 파일을 활용하는 방법이 존재한다.
    - 먼저 자동설정 프로젝트에 해당하는 설정을 properties파일을 활용하도록 변경

```properties
holoman.name=june
holoman.howlong=1000
```
        
```java
@ConfigurationProperties("holoman")
public class HolomanProperties {

    private String name;
    private Long howlong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHowlong() {
        return howlong;
    }

    public void setHowlong(Long howlong) {
        this.howlong = howlong;
    }
}

@Configuration
/**
 * Properties로 설정하는 클래스를 import한다
 */
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {

    /**
     * 해당 타입의 빈이 없을때만 빈을 등록하도록 설정
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public HoloMan holoMan(HolomanProperties properties) {
        HoloMan holoMan = new HoloMan();
        holoMan.setHowLong(properties.getHowlong());
        holoMan.setName(properties.getName());
        return holoMan;
    }
}
```
- 실제 사용하는 프로젝트에서는 빈을 재정의 할 필요없이 , properties만 재정의 해주면 된다.

# Spring boot 원리 - 내장 서버의 이해
- 스프링 부트는 웹서버가 아니다.
    - Tomcat , Netty , Jetty , Undertow 등 을 사용할 수 있도록 자바코드로 제공한다.

```java
        // 톰캣 객체생성
        Tomcat tomcat = new Tomcat();
        // 톰캣 포트설정
        tomcat.setPort(8080);
        // 컨텍스트 설정
        Context context = tomcat.addContext("/", "/");

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("<html><head><title>HelloTomcat</title></head>");
                writer.println("<body>Hello !!! </body></html>");
            }
        };


        // 톰캣에 서블릿 추가
        tomcat.addServlet("/","hello",servlet);
        // 컨텍스트에 서블릿매핑 추
        context.addServletMappingDecoded("/hello","hello");

        // 톰캣실행
        tomcat.start();

        tomcat.getServer().await();

```

- 이 모든 내장 톰캣 설정이 스프링부트의 자동설정에서 유연하게 제공해준다.
- ServletWebServerFactoryAutoConfiguration.class에 의해 설정이된다.
    - TomcatServletWebServerFactoryCustomizer (서버 커스터마이징)
- DispatcherServletAutoConfiguration - 디스패처서블릿 자동설정    


# Spring boot 원리 - 내장 서버 응용
- Spring boot 는 기본적으로 Tomcat을 내장서버로 자동 설정된다.
- 다른 서블릿 컨테이너로 변경도 가능하다.
    - 언더토우로 변경하는 방법
    - 1. spring-boot-start-web에는 기본적으로 tomcat이 함께 의존성으로 들어오기때문에 해당 부분을 제외시켜주어야한다.
    - 2. 그런다음 jetty , netty 등 사용하고싶은 starter 프로젝트를 의존성으로 추가하면 해당 웹서버로 구동이 가능하다.
```xml
<!--   스프링부트 웹 의존성  -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
<!--           톰캣을 기본 의존성에서 제거  -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
<!--        언더토우 내장 서버 로 의존성 추가 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
        </dependency>
```
- 의존성으로 웹서버가 존재 한다면 스프링 부트는 기본적으로 웹 어플리케이션으로 실행한다.

- 스프링부트의 다양한 웹서버 설정 방법
    - properties 파일을 활용하여 , 포트 등 다양한 웹서버 설정을 변경 가능하다.
```properties
## 스프링을 웹애플리케이션이 아닌타입으로 실행
#spring.main.web-application-type=none

## 스프링 웹서버의 포트변경
#server.port=7070

## 스프링 웹서버의 포트를 랜덤으로 지정
server.port=0

```

- 스프링 부트 웹서버가 초기화되었을때 콜백되는 이벤트 리스너 설정
- 스프링 부트에서 권장하는 베스트프렉티스이다.
```java
@Component
//ServletWebServerInitializedEvent < WebServer가 초기화가되면 해당 이벤트에대한 콜백이 이루어짐
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent.getApplicationContext();
        System.out.println(applicationContext.getWebServer().getPort());
    }
}
```
