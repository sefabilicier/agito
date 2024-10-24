package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerPaymentMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Repository.Abstracts.CustomerPaymentRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
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

import static intern.customer.agitoo.Service.Rules.CommonBusinessRules.checkIfIdExist;
import static intern.customer.agitoo.Service.Rules.toDatabase.isConnected;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPaymentServiceImpl implements ICustomerPaymentService {

    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Autowired
    private CustomerPaymentMapper customerPaymentMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-payment")
    public CompletableFuture<List<CustomerPaymentDTO>> getAll () {
        isConnected ();
        List<CustomerPayment> customerPayments = customerPaymentRepository.findAll ();
        List<CustomerPaymentDTO> customerPaymentDTOS = customerPayments
                .stream ()
                .map (customerPayment -> customerPaymentMapper
                        .toDTO (customerPayment, CustomerPaymentDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerPaymentDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-payment", key = "#result.paymentID")
    public CompletableFuture<CustomerPaymentDTO> add (CustomerPaymentDTO dtoModel) {
        CustomerPayment customerPayment = customerPaymentMapper.toEntity (dtoModel, CustomerPayment.class);
        CustomerPayment savedCustomerPayment = customerPaymentRepository.save (customerPayment);
        return CompletableFuture.completedFuture (customerPaymentMapper.toDTO (savedCustomerPayment, CustomerPaymentDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-payment", key = "#result.paymentID")
    public CompletableFuture<CustomerPaymentDTO> update (CustomerPaymentDTO dtoModel) {
        CustomerPayment customerPayment = customerPaymentMapper.toEntity (dtoModel, CustomerPayment.class);
        CustomerPayment updatedCustomerPayment = customerPaymentRepository.save (customerPayment);
        return CompletableFuture.completedFuture (customerPaymentMapper.toDTO (updatedCustomerPayment, CustomerPaymentDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-payment", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerPaymentRepository, id);
        customerPaymentRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-payment", key = "#id")
    public CompletableFuture<CustomerPaymentDTO> findById (Long id) {
        checkIfIdExist (customerPaymentRepository, id);
        CustomerPayment customerPayment = customerPaymentRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer payment not found with id: " + id));
        return CompletableFuture.completedFuture (customerPaymentMapper.toDTO (customerPayment, CustomerPaymentDTO.class));
    }
}
