package api.TestAPI.service;

import api.TestAPI.dto.UserDto;
import api.TestAPI.entity.User;
import api.TestAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableAsync
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public List<User> showUsers() {
        return userRepository.findAll();
    }

    public Optional<User> showById(int id) {
        return userRepository.findById(id);
    }

    public List<User> showByBirthday(Date fromDate, Date toDate) {
        return userRepository.findAllByBirthdayBetween(fromDate, toDate);
        //TODO
    }

    @Transactional
    public void save (UserDto userDto) {
        User user = convertUserDto(userDto);
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, UserDto userDto) {
        User user = convertUserDto(userDto);
        user.setId(id);
        userRepository.save(user);
    }

    public static User convertUserDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setBirthday(userDto.getBirthday());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());

        return user;
    }

}
