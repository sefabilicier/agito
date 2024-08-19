package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPolicyRenewalDTO {

    @NotBlank
    @FutureOrPresent
    private Date renewalDate;
    @NotBlank
    @PositiveOrZero
    private BigDecimal renewalPremium;
    @NotBlank
    @PositiveOrZero
    private BigDecimal renewalCoverageAmount;

    private CustomerPolicyDTO customerPolicyRenewal;
}
