package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.CustomerContactType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerContactDTO {

    @NotBlank(message = "{contactName.notBlank}")
    private String contactName;

    @NotBlank(message = "{contactType.notBlank}")
    private CustomerContactType contactType;

    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.invalid}")
    private String email;

    @NotBlank(message = "{phone.notBlank}")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "{phone.pattern}")
    private String phone;

//    @Valid
//    private CustomerDTO customer;
}
