package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerPolicyMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Repository.Abstracts.CustomerPolicyRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPolicyServiceImpl implements ICustomerPolicyService {

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;

    @Autowired
    private CustomerPolicyMapper customerPolicyMapper;


    @Override
    public List<CustomerPolicyDTO> getAll () {
        List<CustomerPolicy> customerPolicies = customerPolicyRepository.findAll ();
        List<CustomerPolicyDTO> customerPolicyDTOS = customerPolicies
                .stream ()
                .map (customerPolicy -> customerPolicyMapper
                        .toDTO (customerPolicy, CustomerPolicyDTO.class)).collect(Collectors.toList());

        return customerPolicyDTOS;
    }

    @Override
    public CustomerPolicyDTO add (CustomerPolicyDTO dtoModel) {
        CustomerPolicy customerPolicy = customerPolicyMapper.toEntity (dtoModel, CustomerPolicy.class);
        CustomerPolicy savedCustomerPolicy = customerPolicyRepository.save (customerPolicy);
        return customerPolicyMapper.toDTO (savedCustomerPolicy, CustomerPolicyDTO.class);
    }

    @Override
    public CustomerPolicyDTO update (CustomerPolicyDTO dtoModel) {
        CustomerPolicy customerPolicy = customerPolicyMapper.toEntity (dtoModel, CustomerPolicy.class);
        CustomerPolicy updatedCustomerPolicy = customerPolicyRepository.save (customerPolicy);

        return customerPolicyMapper.toDTO (updatedCustomerPolicy, CustomerPolicyDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerPolicyRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
