package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Models.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPaymentDTO {


    @NotNull(message = "{paymentID.notNull}")
    @Positive(message = "{paymentID.positive}")
    private Long paymentID;

    @NotNull(message = "{paymentAmount.notNull}")
    @DecimalMin(value = "0.0", inclusive = true, message = "paymentAmount.positiveOrZero}")
    private BigDecimal paymentAmount;

    @NotNull(message = "{paymentDate.notNull}")
    @PastOrPresent(message = "{paymentDate.pastOrPresent}")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{paymentMethod.notNull}")
    private PaymentMethod paymentMethod;

    @JsonIgnore
    private Customer customer;
    private Long customerId;

    @JsonIgnore
    private CustomerPolicy customerPolicy;
    private Long customerPolicyId;

}
