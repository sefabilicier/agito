package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressCountryMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCountryRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CustomerAddressCountryDTO> getAll () {
        List<CustomerAddressCountry> customerAddressCountries = customerAddressCountryRepository.findAll ();
        List<CustomerAddressCountryDTO> customerAddressCountryDTOS = customerAddressCountries
                .stream ()
                .map (customerAddressCountry -> customerAddressCountryMapper
                        .toDTO (customerAddressCountry, CustomerAddressCountryDTO.class)).collect(Collectors.toList ());
        return customerAddressCountryDTOS;
    }

    @Override
    public CustomerAddressCountryDTO add (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper.toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class);
    }

    @Override
    public CustomerAddressCountryDTO update (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper
                .toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerAddressCountryRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}


