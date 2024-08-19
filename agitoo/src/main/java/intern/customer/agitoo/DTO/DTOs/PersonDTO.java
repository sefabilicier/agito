package intern.customer.agitoo.DTO.DTOs;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    //full name = firstName + middle name + lastName
    @NotBlank
    private String firstName;
    @Nullable
    private String middleName;
    @NotBlank
    private String lastName;

    private String fullName;

    @NotBlank
    private Character gender;
    @NotBlank
    private String maritalStatus;
    @NotBlank
    private String nationality;
    @NotBlank
    private String occupation;

    List<PersonActivityDTO> personActivities;
    List<PersonFeedbackDTO> personFeedbacks;
    List<PersonJobLifeDTO> personJobLifes;
    List<PersonSupportTicketDTO> personSupportTickets;


    public String setFullName (){
        fullName = firstName + middleName + lastName;
        return fullName;
    }

    private CustomerDTO customer;
}
