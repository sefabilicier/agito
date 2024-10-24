package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerMapper;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Helper.Messages.REMOVED;
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
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer") //no need to add key value bc nothing yields
    public CompletableFuture<List<CustomerDTO>> getAll () {
        isConnected ();
        List<Customer> customers = customerRepository.findAll ();
        List<CustomerDTO> customerDTOList = customers
                .stream ()
                .map (customer -> customerMapper
                        .toDTO (customer, CustomerDTO.class)).collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerDTOList);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer", key = "#result.customerId")
    public CompletableFuture<CustomerDTO> add (CustomerDTO dtoModel) {
        Customer customer = customerMapper.toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return CompletableFuture.completedFuture (customerMapper.toDTO (savedCustomer, CustomerDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer", key = "#result.customerId")
    public CompletableFuture<CustomerDTO> update (CustomerDTO dtoModel) {
        Customer customer = customerMapper
                .toEntity (dtoModel, Customer.class);
        Customer savedCustomer = customerRepository.save (customer);

        return CompletableFuture.completedFuture (customerMapper.toDTO (savedCustomer, CustomerDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerRepository, id);
        customerRepository.deleteById (id);
        System.out.print (id + " " + REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer", key = "#id")
    public CompletableFuture<CustomerDTO> findById (Long id) {
        checkIfIdExist (customerRepository, id);
        Customer customer = customerRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer not found with id: " + id));
        return CompletableFuture.completedFuture (customerMapper.toDTO (customer, CustomerDTO.class));
    }


    public void executionInfo () {
        System.out.println ("it is being executed");
    }


}
