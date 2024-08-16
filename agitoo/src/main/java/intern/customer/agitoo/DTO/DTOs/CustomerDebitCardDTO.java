package intern.customer.agitoo.DTO.DTOs;

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

    /*Kart bilgileri client-server arasında verilecek
    fakat maskeli halde verilecek.*/

    private String cardNumber;
    private String cardHolderName;
    private Date expirationDate;
    private String issuer;

}
