package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.*;
import intern.customer.agitoo.Models.enums.CustomerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Long customerId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{customerType.notNull}")
    private CustomerType customerType;

    @JsonIgnore
    private CustomerRegistration customerRegistration;
    private Long customerRegistrationId;

    @JsonIgnore
    private List<Company> companies;

    @JsonIgnore
    private List<Person> list;

    @JsonIgnore
    private List<CustomerAddress> customerAddresses;

    @JsonIgnore
    private List<CustomerContact> customerContacts;

    @JsonIgnore
    private List<CustomerDebitCard> customerDebitCards;

    @JsonIgnore
    private List<CustomerPayment> customerPayments;

    @JsonIgnore
    private List<CustomerPolicy> customerPolicies;

}
