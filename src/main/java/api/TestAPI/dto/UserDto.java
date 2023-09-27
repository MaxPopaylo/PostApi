package api.TestAPI.dto;

import api.TestAPI.utils.validations.ValidAge;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDto {

    @Email
    @NotNull(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Name cannot be empty")
    @Column(name = "first_name")
    private String first_name;

    @NotNull(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String last_name;

    @ValidAge
    @Column(name = "birthday")
    @NotNull(message = "Birthday cannot be empty")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid birthday format. Please use the format '10-10-2000'")
    private Date birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}
