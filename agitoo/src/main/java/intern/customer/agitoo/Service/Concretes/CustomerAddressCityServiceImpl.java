package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCityRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressCityServiceImpl implements ICustomerAddressCityService {

    @Autowired
    private CustomerAddressCityRepository customerAddressCityRepository;


    @Override
    public DataResult<List<CustomerAddressCity>> getAll () {
        return new SuccessDataResult<List<CustomerAddressCity>> (
                this.customerAddressCityRepository.findAll (),
                "Customer address cities listed!"
        );
    }

    @Override
    public Result Add (CustomerAddressCity entity) {
        this.customerAddressCityRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer address city added!"
        );
    }

    @Override
    public Result Update (CustomerAddressCity entity) {
        if (customerAddressCityRepository.existsById (entity.getCityID ())) {
            this.customerAddressCityRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer address city updated!");
        } else {
            return new ErrorResult (
                    false,
                    "Customer address city not found!"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerAddressCityRepository.existsById (id)) {
            customerAddressCityRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer Address City removed"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer Address City not found"
            );
        }
    }
}

