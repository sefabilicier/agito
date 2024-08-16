package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERADDRESSES")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESSID")
    private Long addressID;

    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESSTYPE", length = 10)
    private AddressType addressType;

    @Column(name = "ADDRESSLINE1")
    private String addressLine1;

    @Column(name = "ADDRESSLINE2")
    private String addressLine2;

    @Column(name = "POSTALCODE", length = 20)
    private String postalCode;

    @Column(name = "ISDEFAULT", length = 1)
    private String isDefault;


    @ManyToOne()
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "ADDRESSCOUNTRYID")
    private CustomerAddressCountry customerAddressCountry;

    @ManyToOne()
    @JoinColumn(name = "ADDRESSCITYID")
    private CustomerAddressCity customerAddressCity;

}
