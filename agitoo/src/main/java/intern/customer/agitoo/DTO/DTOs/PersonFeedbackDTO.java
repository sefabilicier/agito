package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.FeedbackType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonFeedbackDTO {

    @NotNull(message = "{feedbackDate.notNull}")
    @PastOrPresent(message = "{feedbackDate.pastOrPresent}")
    private LocalDateTime feedbackDate;

    @NotNull(message = "{feedbackType.notBlank}")
    private FeedbackType feedbackType;

    @NotBlank
    @Min(value = 0, message = "{rating.min}")
    @Max(value = 5, message = "{rating.max}")
    private int rating;

    @NotBlank(message = "{comments.notBlank}")
    private String comments;

//    @Valid
//    private PersonDTO person;
}
