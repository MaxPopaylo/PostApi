package api.service;

import api.dto.SearchByBirthdayDto;
import api.dto.UserDto;
import api.entity.User;
import api.repository.UserRepository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Mock
    private UserRepository userRepository;

    @Test
    public void showUsersTest() throws Exception {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("a.teslia@gmail.com");
        user1.setFirst_name("Alice");
        user1.setLast_name("Teslia");
        user1.setBirthday(dateFormat.parse("2000-01-01"));
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("v.pasternak@gmail.com");
        user2.setFirst_name("Victor");
        user2.setLast_name("Pasternak");
        user2.setBirthday(dateFormat.parse("2000-01-01"));
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.showUsers();

        assertThat(result).isNotNull();
        assertEquals(users.size(), result.size());
        assertEquals(users, result);
    }

    @Test
    public void showByIdTest() throws Exception {
        int user_id = 1;
        User user = new User();
        user.setId(user_id);
        user.setEmail("a.teslia@gmail.com");
        user.setFirst_name("User1");
        user.setLast_name("Test");
        user.setBirthday(dateFormat.parse("2000-01-01"));

        Mockito.when(userRepository.findById(user_id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.showById(user_id);

        assertThat(result.isPresent()).isTrue();
        assertEquals(user, result.get());
    }

    @Test
    public void showByBirthdayTest() throws Exception {
        Date fromDate = dateFormat.parse("2000-01-01");
        Date toDate = dateFormat.parse("2006-01-01");

        List<User> usersInRange = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("user1@gmail.com");
        user1.setFirst_name("User1");
        user1.setLast_name("Test");
        user1.setBirthday(dateFormat.parse("2002-03-15"));
        usersInRange.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user2@gmail.com");
        user2.setFirst_name("User2");
        user2.setLast_name("Test");
        user2.setBirthday(dateFormat.parse("2004-08-20"));
        usersInRange.add(user2);

        SearchByBirthdayDto dto = new SearchByBirthdayDto();
        dto.setFrom_date(fromDate);
        dto.setTo_date(toDate);

        Mockito.when(userRepository.findAllByBirthdayBetween(fromDate, toDate)).thenReturn(usersInRange);

        List<User> users = userService.showByBirthday(dto);

        assertThat(users).isNotNull();
        assertEquals(2, users.size());

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

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(3);
            return savedUser;
        });

        userService.save(userDto);

        Mockito.verify(userRepository).save(Mockito.argThat(savedUser -> {
            assertEquals("newemail@gmail.com", savedUser.getEmail());
            assertEquals("New", savedUser.getFirst_name());
            assertEquals("User", savedUser.getLast_name());
            return true;
        }));
    }


    @Test
    public void deleteTest() {
        int user_id = 1;
        Mockito.doNothing().when(userRepository).deleteById(user_id);

        userService.delete(user_id);
        Mockito.verify(userRepository).deleteById(user_id);
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

        Mockito.verify(userRepository).save(Mockito.argThat(savedUser -> {
            assertEquals("newemail@gmail.com", savedUser.getEmail());
            assertEquals("New", savedUser.getFirst_name());
            assertEquals("User", savedUser.getLast_name());
            return true;
        }));

    }

}
