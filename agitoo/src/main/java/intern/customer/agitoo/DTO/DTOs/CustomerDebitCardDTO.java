package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDebitCardDTO {

    @NotBlank(message = "{cardNumber.notBlank}")
    @Size(min = 16, max = 16, message = "{cardNumber.size}")
    private String cardNumber;

    @NotBlank(message = "{cardHolderName.notBlank}")
    private String cardHolderName;

    @FutureOrPresent(message = "{expirationDate.futureOrPresent}")
    private Date expirationDate;

    @NotBlank(message = "{issuer.notBlank}")
    private String issuer;

//    @Valid
//    private CustomerDTO customer;

    public String getCardNumber () { return maskedCardNumber ();}
    public boolean isCardNumberValid() { return isValidLuhn (cardNumber);}

    /*masking the rest of the card number except the first 4 numbers.*/
    private String maskedCardNumber () {
        if (cardNumber == null || cardNumber.length () < 4) {
            return "invalid card number";
        }
        return cardNumber.substring (0, 4) + "-****-****-****";
    }

    private boolean isValidLuhn (String cardNumber) {
        if (cardNumber == null || cardNumber.length () < 16)
        {
            return false;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length () - 1; i >= 0; i--)
        {
            char c = cardNumber.charAt (i);
            if (!Character.isDigit (c)) {
                return false;
            }
            int n = Character.getNumericValue (c);

            if (alternate){
                n *= 2;
                if (n > 9){
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

}
