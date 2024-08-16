package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.PaymentMethod;
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

    private Date paymentDate;
    private PaymentMethod paymentMethod;


}
