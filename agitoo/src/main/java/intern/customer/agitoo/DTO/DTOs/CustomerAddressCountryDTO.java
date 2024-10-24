package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
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
public class CustomerAddressCountryDTO {

    private Long addressCountryId;

    @NotBlank(message = "{countryName.notBlank}")
    private String countryName; //TODO make it enum

    @JsonIgnore
    private List<CustomerAddressCity> customerAddressCities;

    @JsonIgnore
    private List<CustomerAddress> customerAddresses;
}
