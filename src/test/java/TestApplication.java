import me.june.Applicaiton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-08
 * Time: 21:03
 **/
@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "me.june.name=JuneZero"
        locations = "classpath:/test.properties"
        )
@SpringBootTest(classes = Applicaiton.class, properties = "me.june.name=June0")
public class TestApplication {

    @Autowired
    Environment environment;

    @Test
    public void SpringBootTest의_properties_애트리뷰트를활용한_properties_오버라이딩() {
        String property = environment.getProperty("me.june.name");
        assertThat(property).isEqualTo("June0");
    }

    @Test
    public void TestPropertySource의_우선순위가_더높다() {
        String property = environment.getProperty("me.june.name");
        assertThat(property).isEqualTo("JuneZero");
    }
}
