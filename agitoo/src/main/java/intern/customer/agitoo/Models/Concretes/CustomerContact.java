package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.CustomerContactType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERCONTACTS")
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACTID")
    private Long contactID;

    @Column(name = "CONTACTNAME")
    private String contactName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACTTYPE")
    private CustomerContactType contactType;

    //@Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    /*@Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
            message = "Invalid phone number format")*/
    @Column(name = "PHONE")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID", nullable = false)
    private Customer customer;

}
