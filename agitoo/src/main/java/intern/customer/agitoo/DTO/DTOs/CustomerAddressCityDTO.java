package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddressCityDTO {

    private Long cityID;

    @NotBlank(message = "{cityName.notBlank}")
    private String cityName; //TODO make it enum

    @JsonIgnore
    private CustomerAddressCountry customerAddressCountry;
    private Long customerAddressCountryId;

    @JsonIgnore
    private List<CustomerAddress> customerAddresses;

}
