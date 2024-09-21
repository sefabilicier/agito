package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules.customerTypeRedirection.selectedCustomerType;

@Slf4j
@Controller //@Controller and @ResponseBody both in one --> @RestController: Combines @Controller and @ResponseBody, which means it returns data (like JSON) directly, bypassing view resolution.
@RequestMapping("/ui/customer/")
public class CustomerUIController {

    @Autowired
    private CustomerServiceImpl customerService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list customers!");
        List<CustomerDTO> customerDTOList = customerService.getAll();
        //countCompanyType();

        model.addAttribute("customers", customerDTOList);
        return "customer/customer_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomer(Model model, CustomerDTO customerDTO){
        log.info ("HTML - Received request to CREATE customer {}", customerDTO);
        model.addAttribute ("customer_add", CustomerType.values ());
        model.addAttribute ("customerDTO", new CustomerDTO());
        return "customer/customer_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerDTO") CustomerDTO customerDTO){
        log.info ("HTML - SAVED customer {}", customerDTO);
        customerService.add (customerDTO);

        return selectedCustomerType (
                customerDTO,
                "redirect:/ui/person/add",
                "redirect:/ui/company/add",
                "redirect:/templates/home"
                );
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String editCustomer(Model model, CustomerDTO customerDTO){
        log.info ("HTML - Received request to EDIT customer {}", customerDTO);
        customerService.update (customerDTO);
        model.addAttribute (customerDTO);
        return "customer/customer_update";

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateCustomer (@ModelAttribute("customerDTO") CustomerDTO customerDTO, Model model) {
        log.info ("HTML - Received request to UPDATE customer {}", customerDTO);
        CustomerDTO updatedCustomer = customerService.update (customerDTO);
        model.addAttribute ("customer", updatedCustomer);
        return "redirect:/ui/customer/get-all";
    }
}