package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerPolicyRenewalMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Repository.Abstracts.CustomerPolicyRenewalRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
import intern.customer.agitoo.Service.Rules.CommonBusinessRules;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPolicyRenewalServiceImpl implements ICustomerPolicyRenewalService {

    @Autowired
    private CustomerPolicyRenewalRepository customerPolicyRenewalRepository;

    @Autowired
    private CustomerPolicyRenewalMapper customerPolicyRenewalMapper;

    @Override
    @Cacheable(value = "customer-policy-renewal")
    public List<CustomerPolicyRenewalDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerPolicyRenewal> customerPolicyRenewals = customerPolicyRenewalRepository.findAll ();
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOS = customerPolicyRenewals
                .stream ()
                .map (customerPolicyRenewal -> customerPolicyRenewalMapper
                        .toDTO (customerPolicyRenewal, CustomerPolicyRenewalDTO.class))
                .collect(Collectors.toList ());
        return customerPolicyRenewalDTOS;
    }

    @Override
    @CachePut(value = "customer-policy-renewal", key = "")
    public CustomerPolicyRenewalDTO add (CustomerPolicyRenewalDTO dtoModel) {
        CustomerPolicyRenewal customerPolicyRenewal = customerPolicyRenewalMapper
                .toEntity (dtoModel, CustomerPolicyRenewal.class);
        CustomerPolicyRenewal savedCustomerPolicyRenewal = customerPolicyRenewalRepository.save (customerPolicyRenewal);
        return customerPolicyRenewalMapper
                .toDTO (savedCustomerPolicyRenewal, CustomerPolicyRenewalDTO.class);
    }

    @Override
    @CachePut(value = "customer-policy-renewal", key = "")
    public CustomerPolicyRenewalDTO update (CustomerPolicyRenewalDTO dtoModel) {
        CustomerPolicyRenewal customerPolicyRenewal = customerPolicyRenewalMapper.toEntity (dtoModel, CustomerPolicyRenewal.class);
        CustomerPolicyRenewal updatedCustomerPolicyRenewal = customerPolicyRenewalRepository.save (customerPolicyRenewal);

        return customerPolicyRenewalMapper.toDTO (updatedCustomerPolicyRenewal, CustomerPolicyRenewalDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-policy-renewal", key = "#id")
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (customerPolicyRenewalRepository, id);
        customerPolicyRenewalRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
