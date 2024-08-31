package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerContactMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import intern.customer.agitoo.Repository.Abstracts.CustomerContactRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
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
public class CustomerContactServiceImpl implements ICustomerContactService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerContactMapper customerContactMapper;

    @Override
    @Cacheable(value = "customer-contact")
    public List<CustomerContactDTO> getAll () {
        isConnected ();
        List<CustomerContact> customerContacts = customerContactRepository.findAll ();
        List<CustomerContactDTO> customerContactDTOS = customerContacts
                .stream ()
                .map (customerContact -> customerContactMapper
                        .toDTO (customerContact, CustomerContactDTO.class)).collect (Collectors.toList ());
        return customerContactDTOS;
    }

    @Override
    @CachePut(value = "customer-contact", key = "")
    public CustomerContactDTO add (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper.toEntity (dtoModel, CustomerContact.class);
        CustomerContact savedCustomerContact = customerContactRepository.save (customerContact);
        return customerContactMapper.toDTO (savedCustomerContact, CustomerContactDTO.class);

    }

    @Override
    @CachePut(value = "customer-contact", key = "")
    public CustomerContactDTO update (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper
                .toEntity (dtoModel, CustomerContact.class);
        CustomerContact updatedCustomerContact = customerContactRepository.save (customerContact);
        return customerContactMapper.toDTO (updatedCustomerContact, CustomerContactDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-contact", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (customerContactRepository, id);
        customerContactRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
