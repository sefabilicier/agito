package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCountryRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressCountryServiceImpl implements ICustomerAddressCountryService {

    @Autowired
    private CustomerAddressCountryRepository customerAddressCountryRepository;


    @Override
    public DataResult<List<CustomerAddressCountry>> getAll () {
        return new SuccessDataResult<List<CustomerAddressCountry>> (
                customerAddressCountryRepository.findAll (),
                "Customer address countries listed!"
        );
    }

    @Override
    public Result Add (CustomerAddressCountry entity) {
        customerAddressCountryRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer address country added!"
        );
    }

    @Override
    public Result Update (CustomerAddressCountry entity) {
        if (customerAddressCountryRepository.existsById (entity.getAddressCountryId ())) {
            customerAddressCountryRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer address country updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer country address not found!"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerAddressCountryRepository.existsById (id)) {
            this.customerAddressCountryRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer address country removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer address country not found!"
            );
        }
    }
}


