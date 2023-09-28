package api.TestAPI.dto;

import api.TestAPI.utils.validations.annotations.ValidAge;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Name cannot be empty")
    private String first_name;

    @NotNull(message = "Last name cannot be empty")
    private String last_name;

    @ValidAge
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthday cannot be empty")
    private Date birthday;

    private String address;
    private String phone;
}
