package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegistrationDTO {

    @NotNull(message = "{isActive.notNull}")
    @Pattern(regexp = "^[YN]$", message = "{isActive.pattern}")
    private String isActive; // y or no --> boolean?

    @PastOrPresent(message = "{registrationDate.pastOrPresent}")
    private Date regsitrationDate;

    @NotNull(message = "{lastLoginDate.notNull}")
    private LocalDateTime lastLoginDate;

    @PositiveOrZero(message = "{loyaltyPoints.positiveOrZero}")
    private int loyaltyPoints;

    @NotNull(message = "{newsLetterSubscription.notNull}")
    @Pattern(regexp = "^[YN]$", message = "{newsLetterSubscription.pattern}")
    private String newsLetterSubscription; //--> boolean?

    @NotNull(message = "{socialMediaHandle.notNull}")
    @Pattern(regexp = "^[\\w]+$", message = "{socialMediaHandle.pattern}")
    private String socialMediaHandle;

    @NotNull(message = "{profilePictureURL.notNull}")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "{profilePictureURL.pattern}")
    private String profilePictureURL;

    @PositiveOrZero(message = "{totalSpent.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{totalSpent.decimalMin}")
    private BigDecimal totalSpent;

//    @Valid
//    private CustomerDTO customer;
}
