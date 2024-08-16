package intern.customer.agitoo.DTO.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerContactDTO {

    private String contactName;
    private String contactType;
    private String email;
    private String phone;


}
