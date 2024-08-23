package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Core.Utilities.BooleanToYesNoConverter;
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

    @Convert(converter = BooleanToYesNoConverter.class)
    @Column(name = "ISDEFAULT", length = 1)
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID", nullable = false) //Bir müşteri adresi, mutlaka bir müşteri ile ilişkili olmalı. Bu yüzden nullable = false eklenmeli.
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESSCOUNTRYID", nullable = false)
    private CustomerAddressCountry customerAddressCountry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESSCITYID")
    private CustomerAddressCity customerAddressCity;

}
