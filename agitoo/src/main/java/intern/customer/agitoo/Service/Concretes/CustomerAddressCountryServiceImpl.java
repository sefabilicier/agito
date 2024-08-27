package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressCountryMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCountryRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
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
public class CustomerAddressCountryServiceImpl implements ICustomerAddressCountryService {

    @Autowired
    private CustomerAddressCountryRepository customerAddressCountryRepository;

    @Autowired
    private CustomerAddressCountryMapper customerAddressCountryMapper;


    @Override
    @Cacheable(value = "customer-address-country")
    public List<CustomerAddressCountryDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerAddressCountry> customerAddressCountries = customerAddressCountryRepository.findAll ();
        List<CustomerAddressCountryDTO> customerAddressCountryDTOS = customerAddressCountries
                .stream ()
                .map (customerAddressCountry -> customerAddressCountryMapper
                        .toDTO (customerAddressCountry, CustomerAddressCountryDTO.class)).collect(Collectors.toList ());
        return customerAddressCountryDTOS;
    }

    @Override
    @CachePut(value = "customer-address-country", key = "")
    public CustomerAddressCountryDTO add (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper.toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class);
    }

    @Override
    @CachePut(value = "customer-address-country", key = "")
    public CustomerAddressCountryDTO update (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper
                .toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-address-country", key = "#id")
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (customerAddressCountryRepository, id);
        customerAddressCountryRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}


