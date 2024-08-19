package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERDEBITCARDS")
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
    private String issuer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

}
