package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPaymentDTO {

    @NotNull
    @PastOrPresent
    private Date paymentDate;
    @NotBlank
    private PaymentMethod paymentMethod;

    private CustomerDTO customer;

}
