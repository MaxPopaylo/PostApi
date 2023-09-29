package api.TestAPI.service;

import api.TestAPI.dto.SearchByBirthdayDto;
import api.TestAPI.dto.UserDto;
import api.TestAPI.entity.User;
import api.TestAPI.repository.UserRepository;
import api.TestAPI.utils.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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

    public List<User> showByBirthday(SearchByBirthdayDto dto) {
        return userRepository.findAllByBirthdayBetween(dto.getFrom_date(), dto.getTo_date());
        //TODO
    }

    @Transactional
    public void save (UserDto dto) {
        User user = convertUserDto(dto);
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, UserDto dto) {
        User user = convertUserDto(dto);
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    private static User convertUserDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .first_name(userDto.getFirst_name())
                .last_name(userDto.getLast_name())
                .birthday(userDto.getBirthday())
                .address(userDto.getAddress())
                .phone(userDto.getPhone())
                .build();
    }

}
