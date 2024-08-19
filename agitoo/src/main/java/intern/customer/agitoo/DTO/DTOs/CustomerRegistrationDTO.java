package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    private String isActive;
    @PastOrPresent
    private Date regsitrationDate;
    @NotBlank
    private LocalDateTime lastLoginDate;
    private int loyaltyPoints;
    @NotBlank
    private String newsLetterSubscription;
    @NotBlank
    private String socialMediaHandle;
    @NotBlank
    private String profilePictureURL;
    @PositiveOrZero
    private BigDecimal totalSpent;
}
