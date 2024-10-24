package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Company;
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

    @NotNull(message = "{financialID.notNull}")
    @Positive(message = "financialID.positive}")
    private Long financialID;

    @PastOrPresent(message = "{financialYear.pastOrPresent}")
    @NotBlank(message = "{financialYear.notBlank}")
    @NotNull(message = "{financialYear.notNull}")
    private String financialYear; //date?

    @NotNull(message = "{revenue.notNull}")
    @PositiveOrZero(message = "{revenue.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{revenue.decimalMin}")
    private BigDecimal revenue;

    @NotNull(message = "{profit.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{profit.positiveOrZero}")
    private BigDecimal profit;

    @NotNull(message = "{expenses.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{expenses.positiveOrZero}")
    private BigDecimal expenses;

    @NotNull(message = "{assets.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{assets.positiveOrZero}")
    private BigDecimal assets;

    @NotNull(message = "{liabilities.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{liabilities.positiveOrZero}")
    private BigDecimal liabilities;

    @JsonIgnore
    private Company companies;
    private Long companyId;


}
