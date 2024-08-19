package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddressDTO {

    @NotBlank
    private AddressType addressType;
    @NotBlank
    private String addressLine1;
    @NotBlank
    private String addressLine2;
    @Positive
    private String postalCode;
    @NotBlank
    private String country;
    @NotBlank
    private String isDefault;

    private CustomerDTO customer;
}
