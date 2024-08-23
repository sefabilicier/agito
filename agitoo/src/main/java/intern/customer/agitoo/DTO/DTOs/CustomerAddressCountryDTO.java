package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddressCountryDTO {

    @NotBlank(message = "{countryName.notBlank}")
    private String countryName;
}
