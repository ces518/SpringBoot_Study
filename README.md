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
