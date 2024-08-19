package intern.customer.agitoo.DTO.DTOs;

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
public class CustomerDTO {

    @NotNull
    @NotBlank
    private String customerType;

    private List<CompanyDTO> companies;
    private List<PersonDTO> personLists;
    private List<CustomerAddressDTO> customerAddresses;
    private List<CustomerContactDTO> customerContacts;
    private List<CustomerDebitCardDTO> customerDebitCards;
    private List<CustomerPaymentDTO> customerPayments;
    private List<CustomerPolicyDTO> customerPolicies;
}
