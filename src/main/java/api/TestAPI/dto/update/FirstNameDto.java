package api.TestAPI.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FirstNameDto {

    @NotNull(message = "Name cannot be empty")
    private String first_name;
}
