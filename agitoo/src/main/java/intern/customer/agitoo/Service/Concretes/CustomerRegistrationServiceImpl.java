package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerRegistrationMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import intern.customer.agitoo.Repository.Abstracts.CustomerRegistrationRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
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
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService {


    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;

    @Autowired
    private CustomerRegistrationMapper customerRegistrationMapper;


    @Override
    public List<CustomerRegistrationDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerRegistration> customerRegistrations = customerRegistrationRepository.findAll ();
        List<CustomerRegistrationDTO> customerRegistrationDTOS = customerRegistrations
                .stream ()
                .map (customerRegistration -> customerRegistrationMapper
                        .toDTO (customerRegistration, CustomerRegistrationDTO.class))
                .collect(Collectors.toList());
        return customerRegistrationDTOS;
    }

    @Override
    public CustomerRegistrationDTO add (CustomerRegistrationDTO dtoModel) {
        CustomerRegistration customerRegistration = customerRegistrationMapper.toEntity (dtoModel, CustomerRegistration.class);
        CustomerRegistration savedCustomerRegistration = customerRegistrationRepository.save (customerRegistration);

        return customerRegistrationMapper.toDTO (savedCustomerRegistration, CustomerRegistrationDTO.class);
    }

    @Override
    public CustomerRegistrationDTO update (CustomerRegistrationDTO dtoModel) {
        CustomerRegistration customerRegistration = customerRegistrationMapper
                .toEntity (dtoModel, CustomerRegistration.class);
        CustomerRegistration updatedCustomerRegistration = customerRegistrationRepository.save (customerRegistration);

        return customerRegistrationMapper.toDTO (updatedCustomerRegistration, CustomerRegistrationDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerRegistrationRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
