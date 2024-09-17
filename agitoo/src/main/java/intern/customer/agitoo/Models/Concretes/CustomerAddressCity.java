package intern.customer.agitoo.Models.Concretes;

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
@Table(name = "CUSTOMERADDRESSCITIES")
@Builder
public class CustomerAddressCity {

    @Id
    @GeneratedValue(generator = "customer_address_city_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_address_city_sequence", sequenceName = "customer_address_city_sequence", allocationSize = 1)
    @Column(name = "ADDRESSCITYID")
    private Long cityID;

    @Column(name = "CITYNAME")
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESSCOUNTRYID", nullable = false)
    private CustomerAddressCountry customerAddressCountry;

    @OneToMany(mappedBy = "customerAddressCity", cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddresses;

}
