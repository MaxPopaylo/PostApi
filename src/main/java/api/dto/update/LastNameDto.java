package api.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LastNameDto {

    @NotNull(message = "Last name cannot be empty")
    private String last_name;
}
