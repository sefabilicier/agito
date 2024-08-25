package intern.customer.agitoo.Models.Concretes;


import intern.customer.agitoo.Models.enums.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERS")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMERID")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CUSTOMERTYPE")
    private CustomerType customerType;


    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    private CustomerRegistration customerRegistration;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Company> companies;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Person> personLists;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerContact> customerContacts;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerDebitCard> customerDebitCards;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerPayment> customerPayments;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerPolicy> customerPolicies;

}
