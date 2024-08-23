package intern.customer.agitoo.DTO.DTOs;


import intern.customer.agitoo.Models.enums.CustomerPolicyType;
import jakarta.validation.Valid;
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

    @NotBlank(message = "{customerPolicyNumber.notBlank}")
    private String customerPolicyNumber;

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

    @Valid
    private List<CustomerClaimDTO> customerClaims;

    @Valid
    private List<CustomerPolicyRenewalDTO> customerPolicyRenewals;

//    @Valid
//    private CustomerDTO customer;


}


