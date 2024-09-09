package intern.customer.agitoo.dto;


import com.github.javafaker.Faker;
import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Data
@DataJpaTest
@SpringJUnitConfig
public class CustomerDebitCardDTOValidationTest {

    private CustomerDebitCardDTO customerDebitCardDTO;
    private Faker faker = new Faker ();
    private static final Logger log = LoggerFactory.getLogger (CustomerDebitCardDTOValidationTest.class);

    @Test
    public void CustomerDebitCard_Verification_Success(){

        CustomerDebitCardDTO customerDebitCardDTO1 =
                CustomerDebitCardDTO
                        .builder()
                        .cardHolderName (faker.name ().toString ())
                        .issuer ("Visa")
                        .cardNumber ("4532015112830366") //that is gonna PASS as it meets to Luhn algorithm
                        .expirationDate (faker.date ().future (30, java.util.concurrent.TimeUnit.DAYS))
                .build();

        boolean isValid = customerDebitCardDTO1.isCardNumberValid ();

        if (isValid){
            log.info ("customerDebitCardDTO1 passes");
        } else {
            log.info ("customerDebitCardDTO1 - Unfo validation is under consideration!");
        }

        assertThat (isValid).isTrue ();
    }

    @Test
    public void CustomerDebitCard_Verification_Fails(){
        CustomerDebitCardDTO customerDebitCardDTO2 =
                CustomerDebitCardDTO
                        .builder()
                        .cardHolderName (faker.name ().toString ())
                        .issuer ("Master Card")
                        .cardNumber ("4532015112830367") //that is gonna FAIL as it does not meet to Luhn algorithm
                        .expirationDate (faker.date ().future (60, java.util.concurrent.TimeUnit.DAYS))
                .build();

        boolean isValid = customerDebitCardDTO2.isCardNumberValid ();

        if (isValid){
            log.info ("customerDebitCardDTO2 passes");
        } else {
            log.info ("customerDebitCardDTO1 - Unfo validation is under consideration!");
        }

        assertThat (isValid).isTrue ();
    }
}
