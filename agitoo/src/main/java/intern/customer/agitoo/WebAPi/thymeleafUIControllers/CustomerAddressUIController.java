package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.Service.Concretes.CustomerAddressServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-address/")
public class CustomerAddressUIController {

    @Autowired
    private CustomerAddressServiceImpl customerAddressService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list addresses!");
        List<CustomerAddressDTO> addressDTOList = customerAddressService.getAll ();
        model.addAttribute ("addresses", addressDTOList);
        return "customer_address/customer_address_get_all";
    }
}
