package intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@AllArgsConstructor
public class countCustomer {

    @Autowired
    private CustomerServiceImpl customerService;

    public Long countCompanyType(){

        List<CustomerDTO> customerDTOList = customerService.getAll();

        Long countingCustomer = customerDTOList.stream().count ();

        return countingCustomer;
    }


}