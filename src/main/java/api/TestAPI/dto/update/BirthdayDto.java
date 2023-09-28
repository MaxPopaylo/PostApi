package api.TestAPI.dto.update;

import api.TestAPI.utils.validations.annotations.ValidAge;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class BirthdayDto {

    @ValidAge
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthday cannot be empty")
    private Date birthday;
}
