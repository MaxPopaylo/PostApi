package api.dto;

import api.entity.Department;
import api.utils.validations.CreateValidation;
import api.utils.validations.UpdateValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EmployeeDto {

    @Email(message = "Invalid email", groups = {CreateValidation.class, UpdateValidation.class})
    private String email;

    @NotNull(message = "Name cannot be empty", groups = CreateValidation.class)
    private String name;

    @NotNull(message = "Surname cannot be empty", groups = CreateValidation.class)
    private String surname;

    @NotNull(message = "Patronymic cannot be empty", groups = CreateValidation.class)
    private String patronymic;

    @NotNull(message = "Phone cannot be empty", groups = CreateValidation.class)
    private String phone;

    private int experience;

    @NotNull(message = "Hired date cannot be empty", groups = CreateValidation.class)
    private LocalDate hired;

    private LocalDate fired;

    @NotNull(message = "Work department cannot be empty", groups = CreateValidation.class)
    private Department work_department;
}
