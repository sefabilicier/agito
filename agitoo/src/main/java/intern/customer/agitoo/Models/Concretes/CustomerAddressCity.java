package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne()
    @JoinColumn(name = "ADDRESSCOUNTRYID")
    private CustomerAddressCountry customerAddressCountry;


}
