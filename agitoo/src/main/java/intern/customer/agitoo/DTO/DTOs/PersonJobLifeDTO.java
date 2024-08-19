package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonJobLifeDTO {

    @NotBlank
    private String jobTitle;
    @NotBlank
    private String department;
    @NotBlank
    private String employmentType;

    private PersonDTO person;
}
