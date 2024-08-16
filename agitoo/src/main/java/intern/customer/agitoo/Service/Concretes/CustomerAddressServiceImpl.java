package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressServiceImpl implements ICustomerAddressService {

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Override
    public DataResult<List<CustomerAddress>> getAll () {
        return new SuccessDataResult<List<CustomerAddress>> (
                customerAddressRepository.findAll (),
                "Customer addresses listed!"
        );
    }

    @Override
    public Result Add (CustomerAddress entity) {
        customerAddressRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer address added!"
        );
    }

    @Override
    public Result Update (CustomerAddress entity) {
        if (customerAddressRepository.existsById (entity.getAddressID ())) {
            customerAddressRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer address updated"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer address not found!"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerAddressRepository.existsById (id)) {
            customerAddressRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer address removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer address not found!"
            );
        }
    }
}
