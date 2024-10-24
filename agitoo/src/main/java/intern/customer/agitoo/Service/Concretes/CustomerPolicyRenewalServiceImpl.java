package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerPolicyRenewalMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Repository.Abstracts.CustomerPolicyRenewalRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
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
public class CustomerPolicyRenewalServiceImpl implements ICustomerPolicyRenewalService {

    @Autowired
    private CustomerPolicyRenewalRepository customerPolicyRenewalRepository;

    @Autowired
    private CustomerPolicyRenewalMapper customerPolicyRenewalMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-policy-renewal")
    public CompletableFuture<List<CustomerPolicyRenewalDTO>> getAll () {
        isConnected ();
        List<CustomerPolicyRenewal> customerPolicyRenewals = customerPolicyRenewalRepository.findAll ();
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOS = customerPolicyRenewals
                .stream ()
                .map (customerPolicyRenewal -> customerPolicyRenewalMapper
                        .toDTO (customerPolicyRenewal, CustomerPolicyRenewalDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerPolicyRenewalDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-policy-renewal", key = "#result.renewalId")
    public CompletableFuture<CustomerPolicyRenewalDTO> add (CustomerPolicyRenewalDTO dtoModel) {
        CustomerPolicyRenewal customerPolicyRenewal = customerPolicyRenewalMapper
                .toEntity (dtoModel, CustomerPolicyRenewal.class);
        CustomerPolicyRenewal savedCustomerPolicyRenewal = customerPolicyRenewalRepository.save (customerPolicyRenewal);
        return CompletableFuture.completedFuture (customerPolicyRenewalMapper
                .toDTO (savedCustomerPolicyRenewal, CustomerPolicyRenewalDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-policy-renewal", key = "#result.renewalId")
    public CompletableFuture<CustomerPolicyRenewalDTO> update (CustomerPolicyRenewalDTO dtoModel) {
        CustomerPolicyRenewal customerPolicyRenewal = customerPolicyRenewalMapper.toEntity (dtoModel, CustomerPolicyRenewal.class);
        CustomerPolicyRenewal updatedCustomerPolicyRenewal = customerPolicyRenewalRepository.save (customerPolicyRenewal);

        return CompletableFuture.completedFuture (customerPolicyRenewalMapper.toDTO (updatedCustomerPolicyRenewal, CustomerPolicyRenewalDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-policy-renewal", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerPolicyRenewalRepository, id);
        customerPolicyRenewalRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        CompletableFuture.completedFuture (null);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-policy-renewal", key = "#id")
    public CompletableFuture<CustomerPolicyRenewalDTO> findById (Long id) {
        checkIfIdExist (customerPolicyRenewalRepository, id);
        CustomerPolicyRenewal customerPolicyRenewal = customerPolicyRenewalRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer policy renewal not found with id: " + id));
        return CompletableFuture.completedFuture (customerPolicyRenewalMapper.toDTO (customerPolicyRenewal, CustomerPolicyRenewalDTO.class));
    }
}
