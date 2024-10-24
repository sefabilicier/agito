package intern.customer.agitoo.DTO.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Models.enums.AddressType;
import intern.customer.agitoo.Models.enums.IsDefault;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Long addressID;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{addressType.notNull}") //notnull for enum
    private AddressType addressType;

    @NotBlank(message = "{addressLine1.notBlank}")
    private String addressLine1;

    @NotBlank(message = "{addressLine2.notBlank}")
    private String addressLine2;

    @Pattern(regexp = "\\d+", message = "{postalCode.pattern}")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{isDefault.notNull}")
    private IsDefault isDefault;


    //@Convert(converter = BooleanToYesNoConverter.class)
    /*
     *
     * Entity Sınıfı:
     *   @Convert anotasyonu ile BooleanToYesNoConverter kullanarak boolean türünü 'Y' ve 'N' stringlerine dönüştürürsünüz.
     *    DTO Sınıfı: boolean türünde alan kullanılır ve @Convert anotasyonuna ihtiyaç duymaz.
     *
     * */
//    @AssertTrue(message = "{isDefault.assertTrue}") //for boolean
//    private boolean isDefault; //change for once at least to be true

    @JsonIgnore
    private Customer customer;
    private Long customerId;

    @JsonIgnore
    private CustomerAddressCountry customerAddressCountry;
    private Long customerAddressCountryId;

    @JsonIgnore
    private CustomerAddressCity customerAddressCity;
    private Long customerAddressCityId;

}
