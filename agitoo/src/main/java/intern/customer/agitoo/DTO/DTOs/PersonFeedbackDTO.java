package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonFeedbackDTO {

    @PastOrPresent
    private Date feedbackDate;
    @NotBlank
    private String feedbackType;
    @NotBlank
    private int rating;
    @NotBlank
    private String comments;

    private PersonDTO person;
}
