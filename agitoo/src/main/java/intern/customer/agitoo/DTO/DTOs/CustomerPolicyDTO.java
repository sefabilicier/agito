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
public class CustomerPolicyDTO {

    private String customerPolicyNumber;
    private String customerPolicyType;
    private Date customerPolicyStartDate;
    private Date customerPolicyEndDate;
    private BigDecimal coverageAmount;
    private BigDecimal premium;

}


