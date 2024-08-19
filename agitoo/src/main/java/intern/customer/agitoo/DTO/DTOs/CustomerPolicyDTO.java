package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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

    @NotBlank
    private String customerPolicyNumber;
    @NotBlank
    private String customerPolicyType;
    @NotBlank
    @PastOrPresent
    private Date customerPolicyStartDate;
    @NotBlank
    @FutureOrPresent
    private Date customerPolicyEndDate;
    @NotBlank
    private BigDecimal coverageAmount;
    @NotBlank
    private BigDecimal premium;

    List<CustomerClaimDTO> customerClaims;
    List<CustomerPolicyRenewalDTO> customerPolicyRenewals;

    private CustomerDTO customer;


}


