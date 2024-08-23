package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.ClaimStatus;
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
@Table(name = "CUSTOMERCLAIMS")
public class CustomerClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMERCLAIMID")
    private Long claimId;

    @Column(name = "CLAIMNUMBER", unique = true)
    private String claimNumber;

    @Column(name = "CLAIMDATE")
    private Date claimDate;

    @Column(name = "CLAIMAMOUNT", precision = 15, scale = 2)
    private BigDecimal claimAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLAIMSTATUS")
    private ClaimStatus claimStatus;

    @Column(name = "DESCRIPTION")
    private String claimDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERPOLICYID", nullable = false)
    private CustomerPolicy customerPolicy;

}
