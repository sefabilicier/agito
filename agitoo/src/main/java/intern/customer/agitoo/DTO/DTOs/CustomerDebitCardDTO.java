package intern.customer.agitoo.DTO.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Card number cannot be blank")
    private String cardNumber;
    @NotBlank
    private String cardHolderName;
    @FutureOrPresent
    private Date expirationDate;
    @NotBlank
    private String issuer;

    private CustomerDTO customer;

    public String getCardNumber(){
        return getMaskedcardNumber();
    }

    /*masking the rest of the card number except the first 4 numbers.*/
    public String getMaskedcardNumber(){
        if (cardNumber == null || cardNumber.length () < 4 ){
            return "invalid card number";
        }
        return cardNumber.substring (0,4) + "-****-****-****";
    };

}
