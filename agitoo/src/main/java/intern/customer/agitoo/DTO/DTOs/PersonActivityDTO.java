package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.ActivityType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Long activityId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{activityType.notBlank}")
    private ActivityType activityType;

    @FutureOrPresent(message = "{activityDate.futureOrPresent}")
    @NotNull(message = "{activityDate.notNull}")
    private LocalDateTime activityDate;

    @NotBlank(message = "{description.notBlank}")
    private String description;

    @JsonIgnore
    private Person person;
    private Long personId;

}
