package api.service;

import api.dto.SearchByBirthdayDto;
import api.dto.UserDto;
import api.entity.User;
import api.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.*;
import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = {"/create-users-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-users-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(locations = "/application-test.yaml")
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Autowired
        private UserRepository userRepository;

        @Bean
        public UserService userService() {
            return new UserService(userRepository);
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Test
    public void showUsersTest() throws Exception {
        List<User> users = userService.showUsers();

        assertThat(users).isNotNull();
        assertEquals(users.size(), 2);

        List<String> userEmails = users.stream().map(User::getEmail).toList();
        assertThat(userEmails).containsExactly("a.teslia@gmail.com", "v.pasternak@gmail.com");

    }

    @Test
    public void showByIdTest() throws Exception {
        Optional<User> result = userService.showById(1);
        assertThat(result.isPresent()).isTrue();

        User user = entityManager.find(User.class, 1);
        assertEquals(result.get(), user);
    }

    @Test
    public void showByBirthdayTest() throws Exception {
        Date fromDate = dateFormat.parse("2000-01-01");
        Date toDate = dateFormat.parse("2006-01-01");

        SearchByBirthdayDto dto = new SearchByBirthdayDto();
        dto.setFrom_date(fromDate);
        dto.setTo_date(toDate);

        List<User> users = userService.showByBirthday(dto);
        assertThat(users).isNotNull();
        assertEquals(users.size(), 2);

        List<Date> usersBirthday = users.stream().map(User::getBirthday).toList();
        boolean allDatesInRange = usersBirthday.stream()
                .allMatch(date -> date.after(fromDate) && date.before(toDate));
        assertTrue(allDatesInRange);

    }

    @Test
    public void saveTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("newemail@gmail.com");
        userDto.setFirst_name("New");
        userDto.setLast_name("User");
        userDto.setBirthday(dateFormat.parse("2000-01-01"));

        userService.save(userDto);

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();

        assertEquals(users.size(), 3);
        assertEquals(users.get(2).getEmail(), userDto.getEmail());
    }

    @Test
    public void deleteTest() throws Exception {
       int user_id = 1;

        userService.delete(user_id);
        User user =  entityManager.find(User.class, user_id);

        assertThat(user).isNull();
    }

    @Test
    public void updateWithDtoTest() throws Exception {
        int user_id = 1;

        UserDto userDto = new UserDto();
        userDto.setEmail("newemail@gmail.com");
        userDto.setFirst_name("New");
        userDto.setLast_name("User");
        userDto.setBirthday(dateFormat.parse("2000-01-01"));

        userService.update(user_id, userDto);
        User user = entityManager.find(User.class, user_id);

        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirst_name(), userDto.getFirst_name());
        assertEquals(user.getLast_name(), userDto.getLast_name());
        assertEquals(user.getBirthday(), userDto.getBirthday());
    }

    @Test
    public void testUpdateWithUser() throws Exception {
        int user_id = 1;

        User user = new User();
        user.setId(user_id);
        user.setEmail("newemail@gmail.com");
        user.setFirst_name("New");
        user.setLast_name("User");
        user.setBirthday(dateFormat.parse("2000-01-01"));

        userService.update(user_id, user);
        User entityUser = entityManager.find(User.class, user_id);

        assertEquals(user, entityUser);
    }
}
