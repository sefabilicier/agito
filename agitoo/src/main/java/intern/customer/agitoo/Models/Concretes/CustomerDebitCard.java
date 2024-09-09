package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.Issuer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERDEBITCARDS")
@Builder
public class CustomerDebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEBITCARDID")
    private Long debitCardID;

    @Column(name = "CARDNUMBER")
    private String cardNumber;

    @Column(name = "CARDHOLDERNAME")
    private String cardHolderName;

    @Column(name = "EXPIRATIONDATE")
    private Date expirationDate;

    @Column(name = "ISSUER")
    private Issuer issuer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID", nullable = false)
    private Customer customer;

}
