package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Models.enums.CustomerPolicyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPolicyDTO {

    @NotNull(message = "{customerPolicyId.notNull}")
    @Positive(message = "{customerPolicyId.positive}")
    private Long customerPolicyId;

    @NotBlank(message = "{customerPolicyNumber.notBlank}")
    private String customerPolicyNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{customerPolicyType.notNull}")
    private CustomerPolicyType customerPolicyType;

    @NotNull(message = "{customerPolicyStartDate.notNull}")
    @PastOrPresent(message = "{customerPolicyStartDate.pastOrPresent}")
    private Date customerPolicyStartDate;

    @NotNull(message = "{customerPolicyEndDate.notNull}")
    @FutureOrPresent(message = "{customerPolicyEndDate.futureOrPresent}")
    private Date customerPolicyEndDate;

    @NotNull(message = "{coverageAmount.notNull}")
    @Positive(message = "{coverageAmount.positive}")
    @DecimalMin(value = "0.00", message = "{coverageAmount.decimalMin}")
    private BigDecimal coverageAmount;

    @NotNull(message = "{premium.notNull}")
    @Positive(message = "{premium.positive}")
    @DecimalMin(value = "0.00", message = "{premium.decimalMin}")
    private BigDecimal premium;


    @JsonIgnore
    private Customer customer;
    private Long customerId;

    @JsonIgnore
    private List<CustomerClaim> customerClaims;

    @JsonIgnore
    private List<CustomerPayment> customerPayments;

    @JsonIgnore
    private List<CustomerPolicyRenewal> customerPolicyRenewals;


}


