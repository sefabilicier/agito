package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERPAYMENTS")
@Builder
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENTID")
    private Long paymentID;

    @Column(name = "PAYMENTDATE")
    private Timestamp paymentDate;

    @Column(name = "PAYMENTAMOUNT")
    private BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENTMETHOD")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERPOLICYID", nullable = false)
    private CustomerPolicy customerPolicy;
}
