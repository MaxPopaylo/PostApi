package api.controller;

import api.dto.DepartmentDto;
import api.entity.Department;
import api.service.DepartmentService;
import api.utils.validations.BindingResultParser;
import api.utils.validations.CreateValidation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable int id) {

        Department department = checkDepartment(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated(CreateValidation.class) @RequestBody DepartmentDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(BindingResultParser.parse(bindingResult));
        }

        service.create(dto);
        return new ResponseEntity<>("User " + dto.getTitle() + " was opened",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> close(@PathVariable int id) {
        Department department = checkDepartment(id);
        service.delete(id);
        return new ResponseEntity<>("Department " + department.getTitle() + " was closed", HttpStatus.OK);
    }

    private Department checkDepartment(int id) {
        Optional<Department> department =service.showById(id);
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department not found");
        }

        return department.get();
    }

}
