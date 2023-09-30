package api.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UtilsTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ageValidTest() throws Exception {
        String email = "email@gmail.com";
        String first_name = "Name";
        String last_name = "LastName";
        String birthday = "2020-01-01";

        String jsonRequestBody = "{\"email\": \"" + email + "\", \"first_name\": \"" + first_name + "\", \"last_name\": \"" + last_name + "\", " +
                "\"birthday\": \"" + birthday + "\"}";

        mvc.perform(MockMvcRequestBuilders
                        .post("/users/add")
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid date or you are younger than 18 years old;")));
    }

    @Test
    public void emailValidationTest() throws Exception {
        String email = "email";
        String first_name = "Name";
        String last_name = "LastName";
        String birthday = "2000-01-01";

        String jsonRequestBody = "{\"email\": \"" + email + "\", \"first_name\": \"" + first_name + "\", \"last_name\": \"" + last_name + "\", " +
                "\"birthday\": \"" + birthday + "\"}";

        mvc.perform(MockMvcRequestBuilders
                        .post("/users/add")
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid email;")));
    }

}
