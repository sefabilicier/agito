package intern.customer.agitoo.DTO.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonJobLifeDTO {

    private String jobTitle;
    private String department;
    private String employmentType;
}
