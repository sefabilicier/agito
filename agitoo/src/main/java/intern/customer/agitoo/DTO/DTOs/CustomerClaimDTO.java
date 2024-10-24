package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Models.enums.ClaimStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CustomerClaimDTO {

    @NotNull(message = "{claimId.notNull}")
    @Positive(message = "{claimId.positive}")
    private Long claimId;

    @NotBlank(message = "{claimNumber.notBlank}")
    @Size(min = 1, max = 20, message = "{claimNumber.size}")
    private String claimNumber;

    @PastOrPresent(message = "{claimDate.pastOrPresent}")
    private Date claimDate;

    @NotNull(message = "{claimAmount.notNull}")
    private BigDecimal claimAmount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{claimStatus.notBlank}")
    private ClaimStatus claimStatus;

    @NotBlank(message = "{claimDescription.notBlank}")
    private String claimDescription;

    @JsonIgnore
    private CustomerPolicy customerPolicy;
    private Long customerPolicyId;

}
