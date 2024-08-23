package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @PastOrPresent(message = "{financialYear.pastOrPresent}")
    @NotBlank(message = "{financialYear.notBlank}")
    @NotNull(message = "{financialYear.notNull}")
    private String financialYear; //date?

    @NotNull(message = "{revenue.notNull}")
    @PositiveOrZero(message = "{revenue.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{revenue.decimalMin}")
    private BigDecimal revenue;

//    @Valid
//    private CompanyDTO company;

}
