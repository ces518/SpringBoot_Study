package me.june.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ThymeleafController.class)
public class ThymeleafControllerTest {

    @Autowired
    MockMvc mockMvc;

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
                .andExpect(model().attribute("name",is("june")));
    }
}
