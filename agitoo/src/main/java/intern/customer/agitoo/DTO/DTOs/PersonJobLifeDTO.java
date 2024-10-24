package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.Currency;
import intern.customer.agitoo.Models.enums.EmploymentType;
import intern.customer.agitoo.Models.enums.IncomeSource;
import intern.customer.agitoo.Models.enums.SalaryFrequency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonJobLifeDTO {

    private Long jobID;

    @NotBlank(message = "{jobTitle.notBlank}")
    private String jobTitle;

    @NotBlank(message = "{department.notBlank}")
    private String department;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{employmentType.notNull}")
    private EmploymentType employmentType;

    @NotNull(message = "{baseSalary.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{baseSalary.nonNegative}")
    private BigDecimal baseSalary;

    @NotNull(message = "{bonus.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{bonus.nonNegative}")
    private BigDecimal bonus;

    @NotNull(message = "{commission.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{commission.nonNegative}")
    private BigDecimal commission;

    @NotNull(message = "{overTime.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{overTime.nonNegative}")
    private BigDecimal overTime;

    @NotNull(message = "{totalAnnualIncome.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{totalAnnualIncome.nonNegative}")
    private BigDecimal totalAnnualIncome;

    @NotNull(message = "{lastSalaryReviewedDate.notNull}")
    private LocalDateTime lastSalaryReviewedDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{salaryFrequency.notNull}")
    private SalaryFrequency salaryFrequency;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{currency.notNull}")
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{incomeSource.notNull}")
    private IncomeSource incomeSource;

    @NotNull(message = "{taxableIncome.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{taxableIncome.nonNegative}")
    private BigDecimal taxableIncome;

    @NotNull(message = "{nonTaxableIncome.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{nonTaxableIncome.nonNegative}")
    private BigDecimal nonTaxableIncome;

    @NotNull(message = "{deductions.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{deductions.nonNegative}")
    private BigDecimal deductions;

    @NotNull(message = "{netIncome.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{netIncome.nonNegative}")
    private BigDecimal netIncome;

    @JsonIgnore
    private Person person;
    private Long personId;
}
