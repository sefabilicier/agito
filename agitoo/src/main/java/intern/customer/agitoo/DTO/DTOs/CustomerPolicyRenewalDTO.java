package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "{renewalId.notNull}")
    @Positive(message = "{renewalId.positive}")
    private Long renewalId;

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

    @JsonIgnore
    private CustomerPolicy customerPolicy;
    private Long customerPolicyId;

}
