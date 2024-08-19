package intern.customer.agitoo.Models.Concretes;


import intern.customer.agitoo.Models.enums.CustomerPolicyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERPOLICIES")
public class CustomerPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMERPOLICYID")
    private Long customerPolicyId;

    @Column(name = "CUSTOMERPOLICYNUMBER")
    private String customerPolicyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMERPOLICYTYPE")
    private CustomerPolicyType customerPolicyType;

    @Column(name = "CUSTOMERPOLICYSTARTDATE")
    private Date customerPolicyStartDate;

    @Column(name = "CUSTOMERPOLICYENDDATE")
    private Date customerPolicyEndDate;

    @Column(name = "COVERAGEAMOUNT")
    private BigDecimal coverageAmount;

    @Column(name = "PREMIUM")
    private BigDecimal premium;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @OneToMany(mappedBy = "customerPolicy", fetch = FetchType.EAGER)
    List<CustomerClaim> customerClaims;

    @OneToMany(mappedBy = "customerPolicy", fetch = FetchType.EAGER)
    List<CustomerPolicyRenewal> customerPolicyRenewals;

}

