package api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.yaml")
@Sql(value = {"/create-users-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-users-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void indexTest() throws Exception {
        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email", is("a.teslia@gmail.com")))
                .andExpect(jsonPath("$[0].first_name", is("Andriy")))
                .andExpect(jsonPath("$[0].last_name", is("Teslia")))
                .andExpect(jsonPath("$[0].birthday", is("2003-07-25")))
                .andExpect(jsonPath("$[0].address", is("pasichna 5")))
                .andExpect(jsonPath("$[0].phone", is("+380234234234")));
    }

    @Test
    public void showTest() throws Exception {
        int user_id = 1;

        mvc.perform(get("/users/" + user_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is("a.teslia@gmail.com")))
                .andExpect(jsonPath("$.first_name", is("Andriy")))
                .andExpect(jsonPath("$.last_name", is("Teslia")))
                .andExpect(jsonPath("$.birthday", is("2003-07-25")))
                .andExpect(jsonPath("$.address", is("pasichna 5")))
                .andExpect(jsonPath("$.phone", is("+380234234234")));
    }

    @Test
    public void indexByBirthdayTest() throws Exception {
        String from_date = "2003-01-01";
        String to_date = "2005-01-01";

        String jsonRequestBody = "{\"from_date\": " + from_date + ", \"to_date\": " + to_date + "}";

        mvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email", is("a.teslia@gmail.com")))
                .andExpect(jsonPath("$[0].first_name", is("Andriy")))
                .andExpect(jsonPath("$[0].last_name", is("Teslia")))
                .andExpect(jsonPath("$[0].birthday", is("2003-07-25")))
                .andExpect(jsonPath("$[0].address", is("pasichna 5")))
                .andExpect(jsonPath("$[0].phone", is("+380234234234")));

    }

    @Test
    public void createTest() throws Exception {
        String email = "email@gmail.com";
        String first_name = "Name";
        String last_name = "LastName";
        String birthday = "2000-01-01";

        String jsonRequestBody = "{\"email\": \"" + email + "\", \"first_name\": \"" + first_name + "\", \"last_name\": \"" + last_name + "\", " +
                "\"birthday\": \"" + birthday + "\"}";

        mvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User email@gmail.com was created"));
    }

    @Test
    public void updateTest() throws Exception {
        int user_id = 1;
        String email = "email@gmail.com";
        String first_name = "Name";
        String last_name = "LastName";
        String birthday = "2000-01-01";

        String jsonRequestBody = "{\"email\": \"" + email + "\", \"first_name\": \"" + first_name + "\", \"last_name\": \"" + last_name + "\", " +
                "\"birthday\": \"" + birthday + "\"}";

        mvc.perform(MockMvcRequestBuilders
                        .patch("/users/" + user_id)
                        .content(jsonRequestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User email@gmail.com was updated"));
    }

    @Test
    public void deleteTest() throws Exception {
        int user_id = 1;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/users/" + user_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
