package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Service.Concretes.CompanyServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/company/")
public class CompanyUIController {

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private CompanyMapper companyMapper;

    //ok
    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list companies!");
        List<CompanyDTO> companyDTO = companyService.getAll ().join ();
        model.addAttribute ("companies", companyDTO);

        return "company/company_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany (@RequestParam(value = "customerId", required = false) Long customerId,
                                 Model model,
                                 CompanyDTO companyDTO) {
        log.info ("HTML - Received request to CREATE company {}", companyDTO);
        model.addAttribute ("companyDTO", companyDTO);

//        model.addAttribute ("customerId", companyDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder().customerId(customerId).build();
//        companyDTO.setCustomer (customer);
        companyDTO.setCustomerId (customerId);

        return "company/company_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCompany (@ModelAttribute("companyDTO") @Valid CompanyDTO companyDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        log.info ("HTML - SAVED customer {}", companyDTO);

        Customer customer = Customer.builder ()
                .customerId (companyDTO.getCustomerId ())
                .build ();
        companyDTO.setCustomer (customer);

        CompanyDTO savedCompanyId = companyService.add (companyDTO).join ();
        redirectAttributes.addFlashAttribute ("companyId", savedCompanyId.getCompanyId ());

//        httpSession.setAttribute ("selectedCompanyId", companyDTO.getCompanyId ());
//
//        Long createdCompanyId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder().customerId(createdCompanyId).build();;
//        companyDTO.setCustomer (customer);

        return "redirect:/ui/company-branch/add?companyId=" + savedCompanyId.getCompanyId ();
    }

    @RequestMapping(value = "/update/{companyId}/edit", method = RequestMethod.GET)
    public String editCompany (@PathVariable("companyId") Long companyId,
                               Model model) {
        log.info ("HTML - Received request to EDIT company with ID {}", companyId);
        CompanyDTO companyFind = companyService.findById (companyId).join ();
        model.addAttribute ("companyDTO", companyFind);
        return "company/company_update";

    }

    @RequestMapping(value = "/update/{companyId}/edit", method = RequestMethod.POST)
    // Should be POST instead of PUT for form submissions
    public String updateCompany (@PathVariable("companyId") Long companyId,
                                 @ModelAttribute("companyDTO") @Valid CompanyDTO companyDTO,
                                 Model model) {
        log.info ("HTML - Received request to UPDATE company {}", companyDTO);
        companyDTO.setCompanyId (companyId);
        CompanyDTO updatedCompany = companyService.update (companyDTO).join ();
        model.addAttribute ("companyDTO", updatedCompany);
        return "redirect:/updating";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("companyDTO") CompanyDTO companyDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", companyDTO);
        CompanyDTO updatedCustomer = companyService.update (companyDTO).join ();
        model.addAttribute ("companyDTO", updatedCustomer);
        return "redirect:/ui/company/get-all";
    }

    @RequestMapping(value = "/delete/{companyId}/delete", method = RequestMethod.DELETE)
    public String deleteCompany (@PathVariable("companyId") Long companyId) {
        log.info ("HTML - Received request to delete for company {}", companyId);
        companyService.deleteById (companyId);
        return "redirect:/deleting";
    }

    @RequestMapping(value = "/confirm-delete", method = RequestMethod.POST)
    public String confirmUpdate (Long companyId) {
        log.info ("HTML - Confirming delete for company {}", companyId);
        companyService.deleteById (companyId);
        return "redirect:/ui/company/get-all";
    }
}