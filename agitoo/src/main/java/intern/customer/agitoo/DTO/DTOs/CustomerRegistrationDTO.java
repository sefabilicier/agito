package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.IsActive;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Long registrationID;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{isActive.notNull}")
    //@Pattern(regexp = "^[YN]$", message = "{isActive.pattern}")
    private IsActive isActive;

    @PastOrPresent(message = "{registrationDate.pastOrPresent}")
    private Date registrationDate;

    @NotNull(message = "{lastLoginDate.notNull}")
    private LocalDateTime lastLoginDate;  //= LocalDateTime.now ();TODO generateable

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

    private Date dateOfLastPurchase;

    @PositiveOrZero(message = "{totalSpent.positiveOrZero}")
    @DecimalMin(value = "0.00", message = "{totalSpent.decimalMin}")
    private BigDecimal totalSpent;

    @JsonIgnore
    private Customer customer;
    private Long customerId;

}
