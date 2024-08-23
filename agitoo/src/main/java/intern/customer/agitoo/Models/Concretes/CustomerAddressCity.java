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
@Table(name = "CUSTOMERADDRESSCITIES")
public class CustomerAddressCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
