package intern.customer.agitoo.Models.Concretes;


import intern.customer.agitoo.Models.enums.Currency;
import intern.customer.agitoo.Models.enums.EmploymentType;
import intern.customer.agitoo.Models.enums.IncomeSource;
import intern.customer.agitoo.Models.enums.SalaryFrequency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal baseSalary;

    @Column(name = "BONUS", precision = 15, scale = 2)
    private BigDecimal bonus;

    @Column(name = "COMMISSION", precision = 15, scale = 2)
    private BigDecimal commission;

    @Column(name = "OVERTIME", precision = 15, scale = 2)
    private BigDecimal overTime;

    @Column(name = "TOTALANNUALINCOME", precision = 15, scale = 2)
    private BigDecimal totalAnnualIncome;

    @Column(name = "LASTSALARYREVIEWDATE")
    private Date lastSalaryReviewedDate;

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
    private BigDecimal taxableIncome;

    @Column(name = "NONTAXABLEINCOME", precision = 15, scale = 2)
    private BigDecimal nonTaxableIncome;

    @Column(name = "DEDUCTIONS", precision = 15, scale = 2)
    private BigDecimal deductions;

    @Column(name = "NETINCOME", precision = 15, scale = 2)
    private BigDecimal netIncome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSONID")
    private Person person;

}
