package intern.customer.agitoo.DTO.DTOs;

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

    private String activityType;
    private LocalDateTime activityDate;
    private String description;
}
