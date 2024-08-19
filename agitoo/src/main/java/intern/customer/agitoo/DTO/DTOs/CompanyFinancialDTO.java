package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyFinancialDTO {

    @PastOrPresent
    private String financialYear;
    @NotBlank
    private BigDecimal revenue;

    private CompanyDTO company;

}
