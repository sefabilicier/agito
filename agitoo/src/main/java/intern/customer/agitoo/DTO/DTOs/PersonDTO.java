package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.*;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL) // Sadece null olmayan değerler JSON'da görünecek
public class PersonDTO {

    private Long personId;

    @NotBlank(message = "{firstName.notBlank}")
    private String firstName;

    @Nullable
    private String middleName;

    @NotBlank(message = "{lastName.notBlank}")
    private String lastName;

//    private String fullName;

    @NotNull(message = "{dateOfBirth.notNull}")
    @Past(message = "{dateOfBirth.Past}")
    private LocalDate dateOfBirth;

    @NotNull(message = "{gender.notBlank}")
    private PersonGender gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{maritalStatus.notBlank}")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "{nationality.notBlank}")
    private String nationality;

    @NotBlank(message = "{occupation.notBlank}")
    private String occupation;

    @JsonIgnore
    private Customer customer;
    private Long customerId;

    @JsonIgnore
    private List<PersonActivity> personActivities;

    @JsonIgnore
    private List<PersonFeedback> personFeedbacks;

    @JsonIgnore
    private List<PersonJobLife> personJobLives;

    @JsonIgnore
    private List<PersonSupportTicket> personSupportTickets;


    public String getFullName () {
        return firstName + (middleName != null ? " " + middleName : "") + " " + lastName;
    }

}
