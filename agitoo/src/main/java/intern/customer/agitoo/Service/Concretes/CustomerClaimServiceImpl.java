package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerClaimMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import intern.customer.agitoo.Repository.Abstracts.CustomerClaimRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
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
public class CustomerClaimServiceImpl implements ICustomerClaimService {

    @Autowired
    private CustomerClaimRepository customerClaimRepository;

    @Autowired
    private CustomerClaimMapper customerClaimMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-claim")
    public CompletableFuture<List<CustomerClaimDTO>> getAll () {
        isConnected ();
        List<CustomerClaim> customerClaims = customerClaimRepository.findAll ();
        List<CustomerClaimDTO> customerClaimDTOS = customerClaims
                .stream ()
                .map (customerClaim -> customerClaimMapper
                        .toDTO (customerClaim, CustomerClaimDTO.class))
                .collect (Collectors.toList ());

        return CompletableFuture.completedFuture (customerClaimDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-claim", key = "#result.claimId")
    public CompletableFuture<CustomerClaimDTO> add (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper
                .toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim savedCustomerClaim = customerClaimRepository.save (customerClaim);
        return CompletableFuture.completedFuture (customerClaimMapper.toDTO (savedCustomerClaim, CustomerClaimDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-claim", key = "#result.claimId")
    public CompletableFuture<CustomerClaimDTO> update (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper.toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim updatedCustomerClaim = customerClaimRepository.save (customerClaim);
        return CompletableFuture.completedFuture (customerClaimMapper.toDTO (updatedCustomerClaim, CustomerClaimDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-claim", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerClaimRepository, id);
        customerClaimRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-claim", key = "#id")
    public CompletableFuture<CustomerClaimDTO> findById (Long id) {
        checkIfIdExist (customerClaimRepository, id);
        CustomerClaim customerClaim = customerClaimRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer Claim not found with id: " + id));
        return CompletableFuture.completedFuture (customerClaimMapper.toDTO (customerClaim, CustomerClaimDTO.class));
    }
}
