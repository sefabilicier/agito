package intern.customer.agitoo.DTO.DTOs;


import intern.customer.agitoo.Models.enums.ClaimStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class CustomerClaimDTO {

    @PastOrPresent(message = "{claimDate.pastOrPresent}")
    private Date claimDate;
    @NotNull(message = "{claimAmount.notNull}")
    private BigDecimal claimAmount;
    @NotNull(message = "{claimStatus.notBlank}")
    private ClaimStatus claimStatus;
    @NotBlank(message = "{claimDescription.notBlank}")
    private String claimDescription;

//    @Valid
//    private CustomerPolicyDTO customerPolicy;
}
