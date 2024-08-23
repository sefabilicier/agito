package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "{firstName.notBlank}")
    @JsonIgnore
    private String firstName;

    @Nullable
    @JsonIgnore
    private String middleName;

    @NotBlank(message = "{lastName.notBlank}")
    @JsonIgnore
    private String lastName;

    private String fullName;

    @NotNull(message = "{gender.notBlank}")
    private PersonGender gender;

    @NotNull(message = "{maritalStatus.notBlank}")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "{nationality.notBlank}")
    private String nationality;

    @NotBlank(message = "{occupation.notBlank}")
    private String occupation;

//    @Valid
//    private List<PersonActivityDTO> personActivities;
//    @Valid
//    private List<PersonFeedbackDTO> personFeedbacks;
    @Valid
    private List<PersonJobLifeDTO> personJobLifes;
//    @Valid
//    private List<PersonSupportTicketDTO> personSupportTickets;
//    @Valid
//    private CustomerDTO customer;

    public String getFullName() {
        return firstName + (middleName != null ? " " + middleName : "") + " " + lastName;
    }

}
