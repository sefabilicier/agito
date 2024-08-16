package intern.customer.agitoo.Models.Concretes;


import intern.customer.agitoo.Models.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMERID")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMERTYPE")
    private CustomerType customerType;


    @OneToOne(mappedBy = "customer")
    private CustomerRegistration customerRegistration;

    @OneToMany(mappedBy = "customer")
    List<Company> companies;

    @OneToMany(mappedBy = "customer")
    List<Person> personLists;

    @OneToMany(mappedBy = "customer")
    List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "customer")
    List<CustomerContact> customerContacts;

    @OneToMany(mappedBy = "customer")
    List<CustomerDebitCard> customerDebitCards;

    @OneToMany(mappedBy = "customer")
    List<CustomerPayment> customerPayments;

    @OneToMany(mappedBy = "customer")
    List<CustomerPolicy> customerPolicies;

}
