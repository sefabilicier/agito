package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAll () {
        toDatabase.isConnected ();
        List<Customer> customers = customerRepository.findAll ();
        List<CustomerDTO> customerDTOS = customers
                .stream ()
                .map (customer -> customerMapper
                        .toDTO (customer, CustomerDTO.class)).collect(Collectors.toList());
        return customerDTOS;
    }

    @Override
    public CustomerDTO add (CustomerDTO dtoModel) {
        Customer customer = customerMapper.toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return customerMapper.toDTO (savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO update (CustomerDTO dtoModel) {
        Customer customer = customerMapper
                .toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return customerMapper.toDTO (savedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }

    public void executionInfo () {
        System.out.println ("it is being executed");
    }


}
