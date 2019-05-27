package me.june.mvc;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ThymeleafController.class)
public class ThymeleafControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebClient webClient;

    @Test
    public void thymeleaf_test() throws Exception {
        // Request: /helloThymeleaf
        // Response:
        //   status: 200
        //   view.name: helloThymeleaf
        //   mode.name: june
        this.mockMvc.perform(get("/helloThymeleaf"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("helloThymeleaf"))
                .andExpect(model().attribute("name", is("june")));
    }

    /**
     * 좀더 HTML에 특화된 테스트를 작성할 수 있다.
     *
     * @throws Exception
     */
    @Test
    public void testWithHtmlUnit() throws Exception {
        // page정보를 가져올때 page interface로 리턴한다.
        // HTMLPage타입을 사용해야지 좀더 다양한 인터페이스를 사용할 수 있다.
        HtmlPage page = this.webClient.getPage("/helloThymeleaf");

        // h1을 하나 쿼리해서 텍스트를 assertion
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assertThat(h1.getTextContent()).isEqualTo("june");
    }
}
