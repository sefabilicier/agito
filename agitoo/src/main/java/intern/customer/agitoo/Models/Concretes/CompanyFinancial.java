package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMPANYFINANCIALS")
@Builder
public class CompanyFinancial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FINANCIALID")
    private Long financialID;

    @Column(name = "FISCALYEAR")
    private String financialYear;

    @Column(name = "REVENUE")
    private BigDecimal revenue;

    @Column(name = "PROFIT")
    private BigDecimal profit;

    @Column(name = "EXPENSES")
    private BigDecimal expenses;

    @Column(name = "ASSETS")
    private BigDecimal assets;

    @Column(name = "LIABILITIES")
    private BigDecimal liabilities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANYID")
    private Company company;

}
