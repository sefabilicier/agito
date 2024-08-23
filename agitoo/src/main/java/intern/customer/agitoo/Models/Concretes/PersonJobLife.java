package intern.customer.agitoo.Models.Concretes;


import intern.customer.agitoo.Models.enums.Currency;
import intern.customer.agitoo.Models.enums.EmploymentType;
import intern.customer.agitoo.Models.enums.IncomeSource;
import intern.customer.agitoo.Models.enums.SalaryFrequency;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONJOBLIFES")
public class PersonJobLife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOBID")
    private Long jobID;

    @Column(name = "JOBTITLE")
    private String jobTitle;

    @Column(name = "DEPARTMENT")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYMENTTYPE")
    private EmploymentType employmentType;

    @Column(name = "BASESALARY", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal baseSalary;

    @Column(name = "BONUS", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal bonus;

    @Column(name = "COMMISSION", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal commission;

    @Column(name = "OVERTIME", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal overTime;

    @Column(name = "TOTALANNUALINCOME", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal totalAnnualIncome;

    @Column(name = "LASTSALARYREVIEWDATE")
    private LocalDateTime lastSalaryReviewedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALARYFREQUENCY")
    private SalaryFrequency salaryFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY")
    private Currency currency; //let us make a table at most 3 characters for currency code

    @Enumerated(EnumType.STRING)
    @Column(name = "INCOMESOURCE")
    private IncomeSource incomeSource;

    @Column(name = "TAXABLEINCOME", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal taxableIncome;

    @Column(name = "NONTAXABLEINCOME", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal nonTaxableIncome;

    @Column(name = "DEDUCTIONS", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal deductions;

    @Column(name = "NETINCOME", precision = 15, scale = 2)
    @DecimalMin (value = "0.00")
    private BigDecimal netIncome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONID")
    private Person person;

}
