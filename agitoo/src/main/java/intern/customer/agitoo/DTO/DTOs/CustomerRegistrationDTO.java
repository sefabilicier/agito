package intern.customer.agitoo.DTO.DTOs;

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

    private String isActive;
    private Date regsitrationDate;
    private LocalDateTime lastLoginDate;
    private int loyaltyPoints;
    private String newsLetterSubscription;
    private String socialMediaHandle;
    private String profilePictureURL;
    private BigDecimal totalSpent;
}
