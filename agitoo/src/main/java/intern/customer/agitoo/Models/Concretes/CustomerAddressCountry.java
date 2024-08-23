package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERADDRESSCOUNTRIES")
public class CustomerAddressCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESSCOUNTRYID")
    private Long addressCountryId;

    @Column(name = "COUNTRYNAME", length = 60)
    private String countryName;


    @OneToMany(mappedBy = "customerAddressCountry", cascade = CascadeType.ALL)
    private List<CustomerAddressCity> cities;

    @OneToMany(mappedBy = "customerAddressCountry", cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddresses;

}