package intern.customer.agitoo.DTO.DTOs;


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

    private Date claimDate;
    private BigDecimal claimAmount;
    private String claimStatus;
    private String claimDescription;

}
