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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerClaimServiceImpl implements ICustomerClaimService {

    @Autowired
    private CustomerClaimRepository customerClaimRepository;

    @Autowired
    private CustomerClaimMapper customerClaimMapper;

    @Override
    public List<CustomerClaimDTO> getAll () {
        List<CustomerClaim> customerClaims = customerClaimRepository.findAll ();
        List<CustomerClaimDTO> customerClaimDTOS = customerClaims
                .stream ()
                .map (customerClaim -> customerClaimMapper
                        .toDTO (customerClaim, CustomerClaimDTO.class))
                .collect (Collectors.toList ());

        return customerClaimDTOS;
    }

    @Override
    public CustomerClaimDTO add (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper
                .toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim savedCustomerClaim = customerClaimRepository.save (customerClaim);
        return customerClaimMapper.toDTO (savedCustomerClaim, CustomerClaimDTO.class);
    }

    @Override
    public CustomerClaimDTO update (CustomerClaimDTO dtoModel) {
        CustomerClaim customerClaim = customerClaimMapper.toEntity (dtoModel, CustomerClaim.class);
        CustomerClaim updatedCustomerClaim = customerClaimRepository.save (customerClaim);
        return customerClaimMapper.toDTO (updatedCustomerClaim, CustomerClaimDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerClaimRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
