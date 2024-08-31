package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerClaimMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import intern.customer.agitoo.Repository.Abstracts.CustomerClaimRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
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
public class CustomerClaimServiceImpl implements ICustomerClaimService {

    @Autowired
    private CustomerClaimRepository customerClaimRepository;

    @Autowired
    private CustomerClaimMapper customerClaimMapper;

    @Override
    @Cacheable(value = "customer-claim")
    public List<CustomerClaimDTO> getAll () {
        isConnected ();
        List<CustomerClaim> customerClaims = customerClaimRepository.findAll ();
        List<CustomerClaimDTO> customerClaimDTOS = customerClaims
                .stream ()
                .map (customerClaim -> customerClaimMapper
                        .toDTO (customerClaim, CustomerClaimDTO.class))
                .collect (Collectors.toList ());

        return customerClaimDTOS;
    }

    @Override
    @CachePut(value = "customer-claim", key = "")
    public CustomerClaimDTO add (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper
                .toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim savedCustomerClaim = customerClaimRepository.save (customerClaim);
        return customerClaimMapper.toDTO (savedCustomerClaim, CustomerClaimDTO.class);
    }

    @Override
    @CachePut(value = "customer-claim", key = "")
    public CustomerClaimDTO update (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper.toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim updatedCustomerClaim = customerClaimRepository.save (customerClaim);
        return customerClaimMapper.toDTO (updatedCustomerClaim, CustomerClaimDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-claim", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (customerClaimRepository, id);
        customerClaimRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
