package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddressDTO {

    private AddressType addressType;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String country;
    private String isDefault;

}
