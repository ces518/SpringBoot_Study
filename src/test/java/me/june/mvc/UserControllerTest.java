package me.june.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Boot 자동설정을 통해서 MVC개발이 바로 가능한지 테스트용 class
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void createUser() throws Exception {

//        User user = new User();
//        String userJson = objectMapper.writeValueAsString(user);

        String userJson = "{\"username\": \"june\", \"password\": \"1234\"}";

        this.mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("june")) // username 이 june으로 나올것이라 예상
                .andExpect(jsonPath("$.password").value("1234")); // password가 1234로 나올것이라 예상
    }

    @Test
    public void createUser_accpetXML() throws Exception {

        String userJson = "{\"username\": \"june\", \"password\": \"1234\"}";

        this.mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
                .content(userJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("june"))
                .andExpect(xpath("/User/password").string("1234"));
//                .andExpect(jsonPath("$.username").value("june")) // username 이 june으로 나올것이라 예상
//                .andExpect(jsonPath("$.password").value("1234")); // password가 1234로 나올것이라 예상
    }


    @Test
    public void GET방식은_SETTER가필요없다() throws Exception {
        MultiValueMap params = new LinkedMultiValueMap<>();
        params.add("username", "june");
        params.add("password", "12345");

        this.mockMvc.perform(get("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("june")) // username 이 june으로 나올것이라 예상
                .andExpect(jsonPath("$.password").value("12345")); // password가 1234로 나올것이라 예상
    }

}
