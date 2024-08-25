package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERPOLICYRENEWALS")
@Builder
public class CustomerPolicyRenewal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENEWALID")
    private Long renewalId;

    @Column(name = "RENEWALDATE")
    private Date renewalDate;

    @Column(name = "RENEWALPREMIUM", length = 15)
    private BigDecimal renewalPremium;

    @Column(name = "RENEWALCOVERAGEAMOUNT", length = 15)
    private BigDecimal renewalCoverageAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERPOLICYID", nullable = false)
    private CustomerPolicy customerPolicy;


}
