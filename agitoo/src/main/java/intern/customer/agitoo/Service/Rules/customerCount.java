//package intern.customer.agitoo.Service.Rules;
//
//import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
//import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class customerCount {
//
//    @Autowired
//    private CustomerServiceImpl customerService;
//
//    public int sizeCompany(){
//        List<CustomerDTO> customerDTOList = customerService.getAll ();
//        long companyCount = customerDTOList
//                .stream()
//                .filter ( customerCount ->
//                        "Company".equals (
//                                customerCount.getCustomerType ())).count ();
//
//        long customerCount = customerDTOList.stream()
//                .filter(c -> "Company".equals(c.getCustomerType()))
//                .count();
//    }
//}
