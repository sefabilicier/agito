package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.PaymentMethod;
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
@Table(name = "CUSTOMERPAYMENTS")
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENTID")
    private Long paymentID;

    @Column(name = "PAYMENTDATE")
    private Date paymentDate;

    @Column(name = "PAYMENTAMOUNT")
    private BigDecimal paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENTMETHOD")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERPOLICYID")
    private CustomerPolicy customerPolicy;
}
