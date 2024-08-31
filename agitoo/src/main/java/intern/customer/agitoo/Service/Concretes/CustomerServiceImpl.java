package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Service.Rules.CommonBusinessRules.checkIfIdExist;
import static intern.customer.agitoo.Service.Rules.toDatabase.isConnected;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Cacheable(value = "customer") //no need to add key value bc nothing yields
    public List<CustomerDTO> getAll () {
        isConnected ();
        List<Customer> customers = customerRepository.findAll ();
        List<CustomerDTO> customerDTOS = customers
                .stream ()
                .map (customer -> customerMapper
                        .toDTO (customer, CustomerDTO.class)).collect (Collectors.toList ());
        return customerDTOS;
    }

    @Override
    @CachePut(value = "customer", key = "")
    public CustomerDTO add (CustomerDTO dtoModel) {
        Customer customer = customerMapper.toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return customerMapper.toDTO (savedCustomer, CustomerDTO.class);
    }

    @Override
    @CachePut(value = "customer", key = "")
    public CustomerDTO update (CustomerDTO dtoModel) {
        Customer customer = customerMapper
                .toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return customerMapper.toDTO (savedCustomer, CustomerDTO.class);
    }

    @Override
    @CacheEvict(value = "customer", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (customerRepository, id);
        customerRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }


    public void executionInfo () {
        System.out.println ("it is being executed");
    }


}
