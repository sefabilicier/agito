package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.CustomerType;
import jakarta.validation.Valid;
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

    @NotNull(message = "{customerType.notNull}")
    private CustomerType customerType;

    @Valid
    private List<CompanyDTO> companies;

    @Valid
    private List<PersonDTO> personLists;


    /*@Valid
    private List<CustomerAddressDTO> customerAddresses;

    @Valid
    private List<CustomerContactDTO> customerContacts;

    @Valid
    private List<CustomerDebitCardDTO> customerDebitCards;

    @Valid
    private List<CustomerPaymentDTO> customerPayments;

    @Valid
    private List<CustomerPolicyDTO> customerPolicies;*/
}
