package intern.customer.agitoo.Models.Concretes;


import jakarta.persistence.*;
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
@Table(name = "CUSTOMERREGISTRATIONS")
public class CustomerRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGISTRATIONID")
    private Long registrationID;

    @Column(name = "ISACTIVE")
    private String isActive;

    @Column(name = "REGISTRATIONDATE")
    private Date regsitrationDate;

    @Column(name = "LASTLOGINDATE")
    private LocalDateTime lastLoginDate;

    @Column(name = "LOYALTYPOINTS")
    private int loyaltyPoints;

    @Column(name = "NEWSLETTERSUBSCRIPTION")
    private String newsLetterSubscription;

    @Column(name = "SOCIALMEDIAHANDLE")
    private String socialMediaHandle;

    @Column(name = "PROFILEPICTUREURL")
    private String profilePictureURL;

    @Column(name = "DATEOFLASTPURCHASE")
    private Date dateOfLastPurchase;

    @Column(name = "TOTALSPENT")
    private BigDecimal totalSpent;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;
}
