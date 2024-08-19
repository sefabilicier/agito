package intern.customer.agitoo.DTO.DTOs;

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

    @NotBlank
    private String contactName;
    @NotBlank
    private String contactType;
    @Email
    private String email;
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
    private String phone;

    private CustomerDTO customer;
}
