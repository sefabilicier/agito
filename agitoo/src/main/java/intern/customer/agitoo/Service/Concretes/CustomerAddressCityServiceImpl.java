package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressCityMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCityRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
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
public class CustomerAddressCityServiceImpl implements ICustomerAddressCityService {

    @Autowired
    private CustomerAddressCityRepository customerAddressCityRepository;

    @Autowired
    private CustomerAddressCityMapper customerAddressCityMapper;

    @Override
    public List<CustomerAddressCityDTO> getAll () {
        toDatabase.isConnected ();
        List<CustomerAddressCity> customerAddressCities = customerAddressCityRepository.findAll ();
        List<CustomerAddressCityDTO> customerAddressCityDTOS = customerAddressCities
                .stream ()
                .map (customerAddressCity -> customerAddressCityMapper.toDTO (customerAddressCity, CustomerAddressCityDTO.class))
                .collect (Collectors.toList ());

        return customerAddressCityDTOS;
    }

    @Override
    public CustomerAddressCityDTO add (CustomerAddressCityDTO dtoModel) {
        CustomerAddressCity customerAddressCity = customerAddressCityMapper.toEntity (dtoModel, CustomerAddressCity.class);
        CustomerAddressCity savedCustomerAddressCity = customerAddressCityRepository.save (customerAddressCity);
        return customerAddressCityMapper.toDTO (savedCustomerAddressCity, CustomerAddressCityDTO.class);
    }

    @Override
    public CustomerAddressCityDTO update (CustomerAddressCityDTO dtoModel) {
        CustomerAddressCity customerAddressCity = customerAddressCityMapper
                .toEntity (dtoModel, CustomerAddressCity.class);
        CustomerAddressCity udpatedCustomerAddressCity = customerAddressCityRepository.save (customerAddressCity);
        return customerAddressCityMapper.toDTO (udpatedCustomerAddressCity, CustomerAddressCityDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (customerAddressCityRepository, id);
        customerAddressCityRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}

