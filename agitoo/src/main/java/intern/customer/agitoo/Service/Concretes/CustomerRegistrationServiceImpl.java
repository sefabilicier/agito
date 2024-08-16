package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import intern.customer.agitoo.Repository.Abstracts.CustomerRegistrationRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService {


    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;


    @Override
    public DataResult<List<CustomerRegistration>> getAll () {
        return new SuccessDataResult<> (
                customerRegistrationRepository.findAll (),
                "Customer registrations listed!"
        );
    }

    @Override
    public Result Add (CustomerRegistration entity) {
        customerRegistrationRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer added!"
        );
    }

    @Override
    public Result Update (CustomerRegistration entity) {
        if (customerRegistrationRepository.existsById (entity.getRegistrationID ())) {
            customerRegistrationRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerRegistrationRepository.existsById (id)) {
            customerRegistrationRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer not found"
            );
        }
    }
}
