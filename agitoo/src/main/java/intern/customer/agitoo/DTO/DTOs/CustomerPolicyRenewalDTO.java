package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "{renewalDate.notNull}")
    @FutureOrPresent(message = "{renewalDate.futureOrPresent}")
    private Date renewalDate;

    @NotNull(message = "{renewalPremium.notNull}")
    @PositiveOrZero(message = "{renewalPremium.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{renewalPremium.decimalMin}")
    private BigDecimal renewalPremium;

    @NotNull(message = "{renewalCoverageAmount.notNull}")
    @PositiveOrZero(message = "{renewalCoverageAmount.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{renewalCoverageAmount.decimalMin}")
    private BigDecimal renewalCoverageAmount;

//    @Valid
//    private CustomerPolicyDTO customerPolicy;
}
