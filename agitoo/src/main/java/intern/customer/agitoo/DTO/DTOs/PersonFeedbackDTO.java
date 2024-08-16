package intern.customer.agitoo.DTO.DTOs;

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

    private Date feedbackDate;
    private String feedbackType;
    private int rating;
    private String comments;
}
