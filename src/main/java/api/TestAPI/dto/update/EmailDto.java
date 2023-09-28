package api.TestAPI.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailDto {

    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be empty")
    private String email;
}
