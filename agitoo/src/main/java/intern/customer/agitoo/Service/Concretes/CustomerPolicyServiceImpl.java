package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Repository.Abstracts.CustomerPolicyRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPolicyServiceImpl implements ICustomerPolicyService {

    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;


    @Override
    public DataResult<List<CustomerPolicy>> getAll () {
        return new SuccessDataResult<List<CustomerPolicy>> (
                customerPolicyRepository.findAll (),
                "Customer policies listed!"
        );
    }

    @Override
    public Result Add (CustomerPolicy entity) {
        customerPolicyRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer policy added!"
        );
    }

    @Override
    public Result Update (CustomerPolicy entity) {
        if (customerPolicyRepository.existsById (entity.getCustomerPolicyId ())) {
            customerPolicyRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer policy successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer policy not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerPolicyRepository.existsById (id)) {
            customerPolicyRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer policy removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer policy not found"
            );
        }
    }
}
