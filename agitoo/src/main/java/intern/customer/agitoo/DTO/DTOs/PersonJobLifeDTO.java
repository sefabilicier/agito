package intern.customer.agitoo.DTO.DTOs;


import intern.customer.agitoo.Models.enums.EmploymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonJobLifeDTO {

    @NotBlank(message = "{jobTitle.notBlank}")
    private String jobTitle;

    @NotBlank(message = "{department.notBlank}")
    private String department;

    @NotNull(message = "{employmentType.notNull}")
    private EmploymentType employmentType;

//    @Valid
//    private PersonDTO person;
}
