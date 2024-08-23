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
public class CustomerAddressCityDTO {

    @NotBlank(message = "{cityName.notBlank}")
    private String cityName;

}
