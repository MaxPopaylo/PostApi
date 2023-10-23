package api.dto;

import api.entity.Department;
import api.entity.Employee;
import api.entity.User;
import api.utils.validations.CreateValidation;
import api.valueobject.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderDto {

    @NotNull(message = "Title cannot be empty", groups = CreateValidation.class)
    private String title;

    @NotNull(message = "Type cannot be empty", groups = CreateValidation.class)
    private String type;

    private BigDecimal parcel_price;

    @NotNull(message = "Price of delivery cannot be empty", groups = CreateValidation.class)
    private BigDecimal delivery_price;

    @NotNull(message = "Sender cannot be empty", groups = CreateValidation.class)
    private User sender;

    @NotNull(message = "Recipient cannot be empty", groups = CreateValidation.class)
    private User recipient;

    @NotNull(message = "Sender department cannot be empty", groups = CreateValidation.class)
    private Department department_sender;

    @NotNull(message = "Recipient department cannot be empty", groups = CreateValidation.class)
    private Department department_recipient;

    @NotNull(message = "Employee cannot be empty", groups = CreateValidation.class)
    private Employee employee;

    @NotNull(message = "Status cannot be empty", groups = CreateValidation.class)
    private Status status;
}
