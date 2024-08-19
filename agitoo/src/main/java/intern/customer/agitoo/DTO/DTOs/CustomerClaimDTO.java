package intern.customer.agitoo.DTO.DTOs;


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

    @PastOrPresent
    private Date claimDate;
    @NotNull
    private BigDecimal claimAmount;
    @NotBlank
    private String claimStatus;
    @NotBlank
    private String claimDescription;

    private CustomerPolicyDTO customerPolicy;
}
