package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.ActivityType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonActivityDTO {

    @NotNull(message = "{activityType.notBlank}")
    private ActivityType activityType;

    @FutureOrPresent(message = "{activityDate.futureOrPresent}")
    @NotNull(message = "{activityDate.notNull}")
    private LocalDateTime activityDate;

    @NotBlank(message = "{description.notBlank}")
    private String description;

//    @Valid
//    private PersonDTO person;
}
