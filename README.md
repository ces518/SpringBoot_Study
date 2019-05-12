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


# Spring boot 원리 - 내장 웹서버에 https, http2 적용하기


#### https 적용하기
1. SSL 인증서를 생성한다.
```
keytool -genkey -alias spring -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 4000

```
2. 인증서에 대한 정보들을 application.properties에 추가
```properties
server.ssl.key-store=keystore.p12
server.ssl.key-store-type=PKCS12
server.ssl.key-store-password=123456
server.ssl.key-alias=spring
```

다음과 같이 curl 명령어로 접근해보면 접근이 되지않고 안내문구가 나오게 된다.
- 그이유는 SSL인증서를 로컬에서 생성했기때문에 pubKey정보를 모르기때문 ...
- -k 옵션을줘서 무시하면 200 코드와 함께 접근이 가능해진다.
```
curl -I --http2 https://localhost:8080
curl -I -k --http2 https://localhost:8080 
```

- 스프링부트는 커넥터를 하나만 사용하는데 해당커넥터에 SSL을 적용해준다.
- 적용 후에는 모든 요청이 https로만 가능해진다.
- https 와 http 모두 받고싶다면 ? 커넥터를 추가해주어야한다.

다음과 같이 Bean으로 TomcatServletWebServerFactory로 커넥터를 생성하여 등록해주고나면
https 와 http 요청 모두 받을 수 있다.
```java
    @Bean
    public ServletWebServerFactory servletWebServerFactory () {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }
```

#### http2 설정하기
- http2를 사용하려면 기본적으로 https가 적용되어있어야한다.
- tomcat 8.5 에서는 추가적인 설정이 필요한데 상당히 복잡하기 떄문에 추천하지않는다.
- 버전을 9버전 으로 올리는것을 추천..
```properties
# http2를 지원하는 설정 (undertow는 https설정이 되어있다면 추가적인 설정이 필요가없다.)
# tomcat 8.5 Version 에서는 추가적인 설정이필요하지만 매우 복잡하다 tomcat 9 로 올리는것을 추천..
server.http2.enabled=true
```


#### 실행가능한 JAR
- spring boot maven plugin 에 관련된 이야기
    - mvn package 명령어를 실행하면 , 실행가능한 JAR파일이 생성된다.
    - spring boot maven plugin 이 해주는 일이다.
    - 앱에 필요한 의존성 들도 같이 jar파일 하나에 같이 들어간다.
    - Java에는 내장 Jar를 읽는 표준이 없다.
    - 과거에는 uber jar를 사용했다.
        - 모든 클래스를 하나로 압축하는 방법을 채택
        - 어떤 라이브러리를 사용하는지 알 수 없다
        
    - 스프링부트 전략
        - 애플리케이션과 라이브러리를 구분
        - org.springframework.boot.loader.jar.JarFile : 내장 jar파일들을 읽어들인다.
        - org.springframework.boot.loader.Launcher : 애플리케이션의 mainClass를 실행한다.

모든 jar파일의 시작은 manifest의 mainClass (자바 기본 스펙)


#### Spring boot 원리 정리

- 의존성 관리
    - parent 가 스프링 부트 의존성 관리의 핵심
    - 스프링부트가 관리하는 주요라이브러리의 버전들
    - spring-boot-starter 프로젝트는 스프링부트가 의존성을 관리하는 프로젝트
    - parent로 받는방법과 denpendencyManagement로 받는것과는 큰 차이가 있다.
    
- 자동 설정
    - 스프링부트는 빈을 두단계에 거쳐서 등록한다.
    - @ComponentScan 
    - @EnableAutoConfiguration
    - @Conditional...

- 내장 웹서버
    - 독립적으로 실행가능한  웹 애플리케이션을 제공함.
    - 스프링부트는 웹서버가 아니다.
    - 웹서버를 자동설정으로 인해 웹서버를 내장으로 사용할 뿐이다.


#### Spring boot 활용 - Spring Application
- Spring Application
    - 기본 로그레벨은 Info 레벨이다.
  
다음은 일반적인 스프링부트 애플리케이션이다.
아래처럼 static메서드를 활용하여 스프링부트 애플리케이션을 실행할경우 
SpringApplication 클래스가 제공하는 다양한 커스터마이징을 활용하기가 어렵기때문에
SpringApplication 클래스의 인스턴스를 생성하여 실행 해야한다.

```java
@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args){
        SpringApplication.run(Applicaiton.class,args);
        //SpringApplication application = new SpringApplication();
        //application.run(args);
    }
}
```

- spring boot application 실행시 ,vm옵션으로 -Ddebug 또는 program argument로 --debug옵션을 주게되면 디버그모드로 애플리케이션이 동작을한다.
- 로그레벨도 Debug레벨도 동작한다.
- 애플리케이션 실행시 출력되는 Debug 로그는 스프링부트가 제공하는 자동설정중 어떠한 설정이 자동설정되었는지, 혹은 어떤 자동설정이 되지않았는지를 알 수 있다.
 
 
- Spring boot FailureAnalyers
    - FailureAnalyers는 스프링 애플리케이션 실행중 에러가 발생했을때 해당 에러메시지를 좀더 깔끔하고 보기 쉽게 출력하도록 도와준다.
    - 스프링부트는 기본적으로 몇몇 FailureAnalyers가 등록되어있으며 직접 등록할수도있다.
    
    
- Spring boot banner
    - 애플리케이션 실행시 보이는 배너를 커스터마이징 할 수있다.
    - src > main > resources > banner.txt || gif || png || jpg 배너파일을 위치시키면 스프링부트 애플리케이션 실행시 해당 배너가 출력된다.      
    - 스프링의 버전 등을 출력할 수 있는 변수들을 제공한다.
    - ${spring-boot.version} 등 ..
    - 일부 변수는 MANIFEST파일이 생성되어야 출력가능하다.
    - 배너를 끄고싶은경우 다음과 같이 애플리케이션 실행시 옵션을 줄수있다.
    
```java
@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication();
         //배너를 끄고싶은 경우
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
```


- Spring Application Builder 클래스를 활용하여 빌더패턴을 사용할 수도있다.

```java
@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args){
        new SpringApplicationBuilder()
                    .sources(Applicaiton.class)
                    .run(args);
    }
}
```

- SpringBootApplication을 실행하는 방법은 총 3가지이다.
    - 1. SpringApplication.run() 을 활용 하는방법
    - 2. SpringApplication의 인스턴스를 생성하여 실행하는방법
    - 3. SpringApplicationBuilder를 활용 하는방법
    - static 메서드를 사용하는 방법의 경우에는 스프링 부트 애플리케이션을 커스터마이징 할수 없으므로 주



- Spring 에서 제공해주는 ApplicationEvent가 존재한다.
    - 이벤트의 다양한 시점이 존재한다.
        - ex) 애플리케이션 구동 후 , 애플리케이션 준비완료, 실패 등등 ..

- 이벤트 리스너 사용시 주의점
    - 애플리케이션 리스너의 이벤트 발생시점이 중요하다.
    
    - 애플리케이션 컨텍스트가 생성 된 후 이벤트일 경우
        - 리스너를 생성한뒤 빈으로 등록되어있다면 , 해당 이벤트가 발생시 이벤트리스너가 콜백된다.
    - 애플리케이션 컨텍스트가 생성되기 이전의 이벤트일 경우
        - 빈으로 등록하더라도 해당 이벤트가 발생하여도 리스너가 동작하지않는다. 
        - 이러한 경우에는 직접 등록을 해주어야한다.
```java
/**
* 애플리케이션 리스너 등록 
* " 애플리케이션 이벤트 발생시점에 주의 할것 " 
*/
// 애플리케이션 컨텍스트 생성이전의 이벤트일경우 필요가 없다.
//@Component
public class SimpleListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("app is started !!");
    }
}
@SpringBootApplication
public class Applicaiton {

    public static void main(String[] args){
        SpringApplication application = new SpringApplication();
        // 애플리케이션 컨텍스트 생성이전의 이벤트일경우 다음과 같이 수동으로 등록을 해주어야한다.
        application.addListeners(new SimpleListener());
        application.run(args);
    }
}
```

- WebApplicationType 설정
    - 스프링부트 애플리케이션의 타입은 NONE, SERVLET , REACTIVE 로 크게 3가지로 분류한다.
        - spring-web-mvc가 존재한다면 기본적으로 SERVLET으로 실행
        - webflux가 존재한다면 (servlet이 없을경우) REACTIVE로 실행한다 
        
- 애플리케이션 아규먼트 사용하기
    - 애플리케이션 실행시 --옵션 으로 들어오는 경우 (program arguments)

- 애플리케이션 실행후 무언가를 실행하고 싶은경우
    - ApplicationRunner (추천)
        - ApplicationArguments (추상화된 API를 사용하여 코딩이 가능함) 를 인자로 받는다.
    - CommandLineRunner 
        - String[] 타입으로 인자를 받는다.
    - @Order 로 순서를 지정할 수 있다. (숫자가 낮을수록 우선순위가 높다.)


# Spring boot 활용 - 외부설정
- 사용가능한 외부설정
    - properties
    - yaml
    - 환경변수
    - 커맨드라인 아규먼트
    
#### properties
- 스프링부트에서 properties를 사용한 외부설정의 경우 application.properties파일을 이용하는것이 일반적이다.

사용방법
- 먼저 properties파일에 사용하고자하는 properties를 정의한다.
```properties
# properties를 이용한 외부설정
me.june.name=JuneYongPark
```

- 가장 기본적이고 쉽게 properties를 사용하는방법
> org.springframework.beans.factory.annotation.Value 애노테이션을 활용
```java
@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${me.june.name}")
    private String name;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println(String.format("name = {%s}", name));
    }
}
// 실행결과
// name = {JuneYongPark}
```

#### 프로퍼티 우선순위
1. 홈 디렉토리에 존재하는 spring-boot-dev-tools.properties
2. 테스트에 존재하는 @TestPropertySource
3. @SpringBootTest 애노테이션의 properties 애트리뷰트
4. 커맨트라인 아규먼트
5. SPRING_APPLICATION_JSON (환경변수 혹은 시스템 프로퍼티) 에 존재하는 프로퍼티
6. ServletConfig 파라메터
7. ServletContext 파라메터
8. java:comp/env JNDI 애트리뷰트
9. System.getProperty() 자바 시스템 프로퍼티
10. OS 환경변수
11. RandomValuePropertySource
12. JAR 밖에 존재하는 특정 프로파일의 application.properties
13. JAR 안에 존재하는 특정 프로파일의 application.properties
14. JAR 밖에 존재하는 application.properties
15. JAR 안에 존재하는 application.properties
16. @PropertySource
17. 기본 프로퍼티
> 우선순위가 높은 프로퍼티로 오버라이딩이 된다.
- 모든 Property들은 Environment 객체를 활용하여 사용할 수 있다.

* Test 를 실행할시 일어나는 일
    - 1. src 하위를 빌드한다.
    - 2. test 하위를 빌드한다.
    - 3. test/resources/application.properties가 존재한다면 , test/하위에 존재하는 properties로 오버라이딩 된다.

* Test용 properties를 사용할때 주의할점
    - src 디렉터리를 먼저 빌드하고 test 디렉터리를 빌드하는데
    - src 에 존재하는 properties에는 존재하지만 , test 에 존재하는 properties에는 존재하지않는 프로퍼티가 있고 , 그 값을 참조하는 경우 예외가 발생한다.
    - 잠정적 버그를 발생시킬 여지가 있음.

* properties 파일 내에서 Random값을 사용하는 방법
- random 이라는 변수가 존재하고 , 범위값도 정해줄 수 있다.

* server.port 에는 random을 사용하지 말아야 하는 이유 ?
    - server.port=0이 랜덤값을 지정해주는데 , random 변수를 사용하는것과 무엇이 다를까 ?
    - server.port=0 으로 랜덤값을 주는경우에는 포트번호를 가용가능한 범위 내에서 랜덤값을 부여하지만
    - random 변수는 그것을 고려하지않은 랜덤값을 부여한다.
    - root 권한이 필요한 포트 등을 고려하지않음
```properties
me.june.age=${random.int}

#port랜덤 지정
server.port=0
``` 

* SpringBootTest의 propertie 애트리뷰트를 활용한 방법
```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Applicaiton.class, properties = "me.june.name=June0")
public class TestApplication {

    @Autowired
    Environment environment;

    @Test
    public void SpringBootTest의_properties_애트리뷰트를활용한_properties_오버라이딩() {
        String property = environment.getProperty("me.june.name");
        assertThat(property).isEqualTo("June0");
    }
}
// 실행 결과
// 테스트 통과
```

* @TestPropertySource 애노테이션을 활용하는 방법
- properties 애트리뷰트로 직접 오버라이딩하거나 ,location으로 properties파일을 명시할 수 있다.
```java
@RunWith(SpringRunner.class)
@TestPropertySource(properties = "me.june.name=JuneZero")
@SpringBootTest(classes = Applicaiton.class, properties = "me.june.name=June0")
public class TestApplication {

    @Autowired
    Environment environment;

    @Test
    public void SpringBootTest의_properties_애트리뷰트를활용한_properties_오버라이딩() {
        String property = environment.getProperty("me.june.name");
        assertThat(property).isEqualTo("June0");
        //실패
    }

    @Test
    public void TestPropertySource의_우선순위가_더높다() {
        String property = environment.getProperty("me.june.name");
        assertThat(property).isEqualTo("JuneZero");
        //성공
    }
}
```

* placeHolder 기능
- properties 파일 내부에서 정의한 변수의 경우에는 다음 라인부터 해당 변수를 재사용 할 수 있다.
```properties
me.june.name=JuneYoung
me.june.fullName= ${me.june.name} Park
```

* application.properties 사용시 주의점
    - classpath 하위에 존재한다면 덮어 쓰기때문에 application.properties를 다른곳에 위치할 수 있다.
    - 1. projectRoot/config 하위
    - 2. projectRoot
    - 3. classpath:/config 하위
    - 4. classpath:하위
    - 숫자가 클수록 우선순위가 높다.


#### 타입세이프 프로퍼티 @ConfigurationProperties
    - 여러 프로퍼티를 묶어서 불러올수 있다.
    - java bean spec을 따라서 프로퍼티값들을  바인딩을 해주기때문에 getter setter가 필요함.
빈으로 등록해서 다른 빈에 주입이 가능하다
- @EnableConfigurationProperties
    - @ConfigurationProperties 를 사용하는 클래스들을 활성화
    - 이미 등록되어 있기 활성화 애노테이션이 등록되어있기 때문에 해당 클래스들을 빈으로 등록해주기만 하면된다.
```java
// 하단 애노테이션에 알림이 뜨는경우
// > 해당 메타정보를 기반으로 자동완성을 제공해주는 플러그인을 추가하라는 알림
@Component
@ConfigurationProperties("me.june")
public class JuneYoungProperties {

    private String name;

    private int age;

    private String fullName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
```

@ConfigurationProperties 동작 테스트
```java
@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${me.june.name}")
    private String name;

    @Value("${me.june.fullName}")
    private String fullName;

    //ConfigurationProperties 활용한 출력
    @Autowired
    private JuneYoungProperties juneYoungProperties;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.println(String.format("name = {%s}", name));
        System.out.println(String.format("fullName = {%s}", fullName));

        System.out.println("================= using ConfigurationProperties =================");
        System.out.println(String.format("name = {%s}", juneYoungProperties.getName()));
        System.out.println(String.format("fullName = {%s}", juneYoungProperties.getFullName()));
    }
}
// 실행결과
//name = {JuneYoung}
//fullName = {JuneYoung Park}
//================= using ConfigurationProperties =================
//name = {JuneYoung}
//fullName = {JuneYoung Park}
```

* Relexed Binding
- camel-case로 작성하지않고 , kebab-case or underscore-case로 작성하여도 바인딩을 해준다.

* Type-Conversion 지원
- 스프링 프레임워크가 지원하는 컨버전을 활용해서 타입 컨버전이 일어남.
- @Duration Unit (스프링부트가 지원하는 컨버전)
    - 특정 시간단위 로 받고싶을경우 바인딩을 지원한다.
```java
@DurationUnit(ChronoUnit.SECONDS)
private Duration secound = Duration.ofSeconds(30);
```    
- @Duration 애노테이션을 사용하지않아도 properties에 값을 할당할때
- s,ms 등 suffix를 통해 Duration으로 바인딩 할수 있도록 지원한다.

```properties
# properties를 이용한 외부설정
me.june.name=JuneYoung
me.june.age=100
me.june.fullName= ${me.june.name} Park
me.june.secound=100s
```

```java
@Component
@ConfigurationProperties("me.june")
public class JuneYoungProperties {

    private String name;

    private int age;

    private String fullName;

//    @DurationUnit(ChronoUnit.SECONDS)
    private Duration secound = Duration.ofSeconds(30);

    public Duration getSecound() {
        return secound;
    }

    public void setSecound(Duration secound) {
        this.secound = secound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
```
- 상단의 설정과 동일한 결과가 나타난다. 


* 프로퍼티 값 검증
- 프로퍼티값이 바인딩 될때 검증이 가능하다.
- 상단에 @Validated 애노테이션을 선언하고, 검증하고싶은 필드상단에 JSR-303 애노테이션 (구현체는 hibernate-validator) 를 활용하여 검증을 할 수 있다.
- FailureAnaylizer 가 에러메시지를 보기좋게 포매팅하여 보여준다.
```java
@Component
@ConfigurationProperties("me.june")
@Validated
public class JuneYoungProperties {

    @NotEmpty
    private String name;

    private int age;

    private String fullName;

//    @DurationUnit(ChronoUnit.SECONDS)
    private Duration secound = Duration.ofSeconds(30);

    public Duration getSecound() {
        return secound;
    }

    public void setSecound(Duration secound) {
        this.secound = secound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
```

* @Value 을 사용할경우 ...
    - SpEL을 지원한다.


# Spring-boot 활용 - 프로파일

@Profile 애노테이션
    - 스프링 프레임워크에서 지원해주는 기능
    - 특정 프로파일에서만 빈을 등록하고싶다 .. 등등 에서 활용
    - @Configuration
    - @Component

각 프로파일별로 설정파일 작성
```java
@Profile("prod")
@Configuration
public class BaseConfiguration {

    @Bean
    public String hello() {
        return "Hello Prod!";
    }
}

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    public String hello() {
        return "Hello Test!";
    }
}
```

properties 파일에 활성화할 프로파일을 정의
```properties
# 프로파일 활성화
spring.profiles.active=prod

```

* profiles도 properties의 우선순위에 영향을 받는다.
- java -jar 옵션으로 실행시 커맨드라인 아규먼트가 우선순위가 더 높으므로 실행시 profiles을 지정하여 실행이 가능함.
```
// maven package 
mvn clean package

// prod 프로파일로 애플리케이션 실행
jar -jar example.jar --spring.profiles.active=prod  
```

* profile용 properties도 생성이 가능하다.
    - application-{profile}.properties 의 명으로 작성이 가능하다.
    - profile용 properties파일이 기본 properties파일보다 우선순위가 높기때문에 해당하는 profile의 properties로 오버라이딩된다.

* properties 내에서 특정 프로파일 설정을 포함시키는 방법
    - spring.profiles.include=프로파일명
```properties
# properties를 이용한 외부설정
me.june.name=prod
me.june.age=100
me.june.fullName= ${me.june.name} Park
me.june.secound=100s


# 프로파일 활성화
spring.profiles.active=prod

# 프로파일 설정 포함
spring.profiles.include=proddb
```


# Spring boot 활용 - 로깅
- 스프링부트는 Commons Logging을 사용한다
- 스프링 코어에서 Commons Logging 에서 사용하기때문에 사용..
- SLF4J를 사용하려면 의존성 설정을 잘해주어야함 ... 

#### 로깅 퍼사드 vs 로거
- Commons Logging , SLF4j 
    - 실제 로깅을 하는 구현체가아니라 추상화 해놓은 API
    - 프레임워크들은 로겅 퍼사드를 이용하여 개발함.
    - 실제 구현체들을 교체 할 수 있도록..
    - JUL, LOG4J2, LogBack 들이 실제 구현

- Spring-JCL (스프링 5)
    - Commons Logging > SLF4j 로 변경할수 있도록 제공 
    - pom.xml 에서 exclusion 하지않아도됨

#### 정리
- Commons Logging > SLF4j > LogBack .. 
- 스프링부트는 최종적으로 LogBack 을 사용한다.


#### 스프링 부트 로깅 
- 기본포맷
    - [날짜] 로깅레벨 PID Thread class..
- --debug (일부 핵심라이브러리만 디버깅모드)
- --trace (모든 로깅을 디버그모드로 ..)
- 컬러출력: spring.output.ansi.enabled
- 파일출력: logging.file(로그파일) logging.path(디렉터리) 설정 
- 10mb마다 아카이빙됨
- 로그레벨: logging.level.packagename=LOGGING_LEVEL (패키지별로 로깅레벨 설정)
