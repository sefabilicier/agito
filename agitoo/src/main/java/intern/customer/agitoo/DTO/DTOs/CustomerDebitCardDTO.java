package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.Issuer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static intern.customer.agitoo.Common.Utilities.LuhnDebitCardValidation.isValidLuhn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDebitCardDTO {

    @NotNull(message = "{debitCardID.notNull}")
    @Positive(message = "{debitCardID.positive}")
    private Long debitCardID;

    @NotBlank(message = "{cardNumber.notBlank}")
    @Size(min = 16, max = 16, message = "{cardNumber.size}")
    private String cardNumber; //TODO : MAKE VALIDATION ON FRONTEND - done

    @NotBlank(message = "{cardHolderName.notBlank}")
    private String cardHolderName;

    @FutureOrPresent(message = "{expirationDate.futureOrPresent}")
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "{issuer.notBlank}")
    private Issuer issuer;

    @JsonIgnore
    private Customer customer;
    private Long customerId;

    public String getCardNumber () {

        if (cardNumber == null || cardNumber.isBlank ()) {
            return null; // Thymeleaf boş bırakır, @NotBlank geçerliliği için hata mesajı döner
        }
        return isCardNumberValid () ? maskedCardNumber () : "invalid card number"; // Geçersizse mesaj döner.

    }

    public boolean isCardNumberValid () {
        return isValidLuhn (cardNumber) && cardNumber != null;
    }

    /*masking the rest of the card number except the first 4 numbers.*/
    private String maskedCardNumber () {
        return cardNumber.length () < 16
                ? "Invalid card number"
                : cardNumber.substring (0, 4) + "-****-****-****";
    }
}
