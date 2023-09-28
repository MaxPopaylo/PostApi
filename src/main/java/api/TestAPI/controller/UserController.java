package api.TestAPI.controller;

import api.TestAPI.dto.update.*;
import api.TestAPI.dto.SearchByBirthdayDto;
import api.TestAPI.dto.UserDto;
import api.TestAPI.entity.User;
import api.TestAPI.service.UserService;
import api.TestAPI.utils.exceptions.InvalidDateException;
import api.TestAPI.utils.exceptions.UserNotFoundException;
import api.TestAPI.utils.validations.BindingResultParser;
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
       Optional<User> user = userService.showById(id);
       if (user.isEmpty()) {
           throw new UserNotFoundException();
       }

       return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/by_birthday")
    public ResponseEntity<?> indexByBirthday(@Valid @RequestBody SearchByBirthdayDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        if (dto.getTo_date().before(dto.getFrom_date())) {
            throw new InvalidDateException();
        }

        return new ResponseEntity<>(userService.showByBirthday(dto), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        userService.save(dto);
        return new ResponseEntity<>("User " + dto.getEmail() + " was created",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        userService.delete(id);
        return new ResponseEntity<>("User " + user.get().getEmail() + " was deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody UserDto dto,
                                    BindingResult bindingResult) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        userService.update(id, dto);
        return new ResponseEntity<>("User " + dto.getEmail() + " was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable int id, @Valid @RequestBody EmailDto email,
                                    BindingResult bindingResult) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        user.get().setEmail(email.getEmail());
        userService.update(id, user.get());
        return new ResponseEntity<>("Email was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/first_name")
    public ResponseEntity<?> updateName(@PathVariable int id, @RequestBody FirstNameDto firs_name) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        user.get().setFirst_name(firs_name.getFirst_name());
        userService.update(id, user.get());
        return new ResponseEntity<>("Name was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/last_name")
    public ResponseEntity<?> updateLastName(@PathVariable int id, @RequestBody LastNameDto last_name) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        user.get().setLast_name(last_name.getLast_name());
        userService.update(id, user.get());
        return new ResponseEntity<>("Last name was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/birthday")
    public ResponseEntity<?> updateBirthday(@PathVariable int id, @Valid @RequestBody BirthdayDto birthday,
                                        BindingResult bindingResult) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        user.get().setBirthday(birthday.getBirthday());
        userService.update(id, user.get());
        return new ResponseEntity<>("Birthday was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/address")
    public ResponseEntity<?> updateAddress(@PathVariable int id, @RequestBody AddressDto address) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        user.get().setAddress(address.getAddress());
        userService.update(id, user.get());
        return new ResponseEntity<>("Address was updated", HttpStatus.OK);
    }

    @PutMapping("/update/{id}/phone")
    public ResponseEntity<?> updatePhone(@PathVariable int id, @RequestBody PhoneDto phone) {
        Optional<User> user = userService.showById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        user.get().setPhone(phone.getPhone());
        userService.update(id, user.get());
        return new ResponseEntity<>("Phone was updated", HttpStatus.OK);
    }

}
