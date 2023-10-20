package api.controller;

import api.dto.SearchByBirthdayDto;
import api.dto.UserDto;
import api.entity.User;
import api.service.UserService;
import api.utils.exceptions.InvalidDateException;
import api.utils.exceptions.UserNotFoundException;
import api.utils.validations.BindingResultParser;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(userService.showUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable int id) {
       User user = checkUser(id);
       return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/by_birthday")
    public ResponseEntity<?> indexByBirthday(@Valid @RequestBody SearchByBirthdayDto dto, BindingResult bindingResult) {
        checkValidation(bindingResult);
        if (dto.getTo_date().before(dto.getFrom_date())) {
            throw new InvalidDateException();
        }

        return new ResponseEntity<>(userService.showByBirthday(dto), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto dto, BindingResult bindingResult) {
        checkValidation(bindingResult);
        userService.save(dto);
        return new ResponseEntity<>("User " + dto.getEmail() + " was created",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User user = checkUser(id);
        userService.delete(id);
        return new ResponseEntity<>("User " + user.getEmail() + " was deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody UserDto dto,
                                    BindingResult bindingResult) {
        checkUser(id);
        checkValidation(bindingResult);

        userService.update(id, dto);
        return new ResponseEntity<>("User " + dto.getEmail() + " was updated", HttpStatus.OK);
    }

    private User checkUser(int id) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    private void checkValidation (BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }
    }

}
