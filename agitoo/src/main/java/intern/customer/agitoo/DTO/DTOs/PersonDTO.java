package intern.customer.agitoo.DTO.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {

    //full name = firstName + middle name + lastName
    private String fullName;
    private Character gender;
    private String maritalStatus;
    private String nationality;
    private String occupation;

}
