package intern.customer.agitoo.DTO.DTOs;

import intern.customer.agitoo.Models.enums.AddressType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAddressDTO {


    @NotNull(message = "{addressType.notNull}") //notnull for enum
    private AddressType addressType;

    @NotBlank(message = "{addressLine1.notBlank}")
    private String addressLine1;

    @NotBlank(message = "{addressLine2.notBlank}")
    private String addressLine2;

    @Pattern(regexp = "\\d+", message = "{postalCode.pattern}")
    private String postalCode;

    @NotBlank(message = "{country.notBlank}")
    private String country;

    //@Convert(converter = BooleanToYesNoConverter.class)
    /*
    *
    * Entity Sınıfı:
    *   @Convert anotasyonu ile BooleanToYesNoConverter kullanarak boolean türünü 'Y' ve 'N' stringlerine dönüştürürsünüz.
    *    DTO Sınıfı: boolean türünde alan kullanılır ve @Convert anotasyonuna ihtiyaç duymaz.
    *
    * */
    @AssertTrue(message = "{isDefault.assertTrue}") //for boolean
    private boolean isDefault; //change for once at least to be true

//    @Valid
//    private CustomerDTO customer;
}
