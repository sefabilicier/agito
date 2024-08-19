package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String activityType;
    @FutureOrPresent
    private LocalDateTime activityDate;
    @NotBlank
    private String description;

    private PersonDTO person;
}
