package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerContactMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import intern.customer.agitoo.Repository.Abstracts.CustomerContactRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
import intern.customer.agitoo.Service.Rules.CommonBusinessRules;
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
public class CustomerContactServiceImpl implements ICustomerContactService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerContactMapper customerContactMapper;

    @Override
    public List<CustomerContactDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerContact> customerContacts = customerContactRepository.findAll ();
        List<CustomerContactDTO> customerContactDTOS = customerContacts
                .stream()
                .map (customerContact -> customerContactMapper
                        .toDTO (customerContact, CustomerContactDTO.class)).collect (Collectors.toList ());
        return customerContactDTOS;
    }

    @Override
    public CustomerContactDTO add (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper.toEntity (dtoModel, CustomerContact.class);
        CustomerContact savedCustomerContact = customerContactRepository.save(customerContact);
        return customerContactMapper.toDTO (savedCustomerContact, CustomerContactDTO.class);

    }

    @Override
    public CustomerContactDTO update (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper
                .toEntity (dtoModel, CustomerContact.class);
        CustomerContact updatedCustomerContact = customerContactRepository.save (customerContact);
        return customerContactMapper.toDTO (updatedCustomerContact, CustomerContactDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (customerContactRepository, id);
        customerContactRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
