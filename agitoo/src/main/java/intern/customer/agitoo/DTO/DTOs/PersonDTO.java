package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//@JsonInclude(JsonInclude.Include.NON_NULL) // Sadece null olmayan değerler JSON'da görünecek
public class PersonDTO {

    //full name = firstName + middle name + lastName and the ones will be ignored by JSON
    @NotBlank
    @JsonIgnore
    private String firstName;

    @Nullable
    @JsonIgnore
    private String middleName;

    @NotBlank
    @JsonIgnore
    private String lastName;

    @JsonProperty("fullNname")
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

    private CustomerDTO customer;

    public String setFullName (){
        fullName = firstName + middleName + lastName;
        return fullName;
    }

}
