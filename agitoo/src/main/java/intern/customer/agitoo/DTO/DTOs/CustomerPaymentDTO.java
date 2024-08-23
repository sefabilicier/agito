package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPaymentDTO {

    @NotNull(message = "{paymentDate.notNull}")
    @PastOrPresent(message = "{paymentDate.pastOrPresent}")
    private Timestamp paymentDate;

    @NotNull(message = "{paymentMethod.notNull}")
    private PaymentMethod paymentMethod;

//    @Valid
//    private CustomerDTO customer;

}
