package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.CustomerContactType;
import jakarta.persistence.*;
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

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @ManyToOne()
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

}
