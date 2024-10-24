package intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Models.enums.CustomerType;
import org.springframework.stereotype.Component;

@Component
public class customerTypeRedirection {

    public static String selectedCustomerType (
            CustomerDTO customerDTO,
            String personRedirect,
            String companyRedirect,
            String mainRedirect) {
        if (
                CustomerType.Person
                        .equals (customerDTO.getCustomerType ())) {
            return personRedirect;
        } else if (
                CustomerType.Company
                        .equals (customerDTO.getCustomerType ())) {
            return companyRedirect;
        } else {
            return mainRedirect;
        }
    }
}
