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


    public String getCardNumber(){
        return maskedcardNumber ();
    }

    /*masking the rest of the card number except the first 4 numbers.*/
    private String maskedcardNumber(){
        if (cardNumber == null || cardNumber.length () < 4 ){
            return "invalid card number";
        }
        return cardNumber.substring (0,4) + "-****-****-****";
    };

}
