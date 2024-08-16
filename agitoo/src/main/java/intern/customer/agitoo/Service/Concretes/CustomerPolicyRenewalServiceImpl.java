package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Repository.Abstracts.CustomerPolicyRenewalRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPolicyRenewalServiceImpl implements ICustomerPolicyRenewalService {

    @Autowired
    private CustomerPolicyRenewalRepository customerPolicyRenewalRepository;


    @Override
    public DataResult<List<CustomerPolicyRenewal>> getAll () {
        return new SuccessDataResult<List<CustomerPolicyRenewal>> (
                customerPolicyRenewalRepository.findAll (),
                "Customer policiy renewals listed!"
        );
    }

    @Override
    public Result Add (CustomerPolicyRenewal entity) {
        customerPolicyRenewalRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer policy renewal added!"
        );
    }

    @Override
    public Result Update (CustomerPolicyRenewal entity) {
        if (customerPolicyRenewalRepository.existsById (entity.getRenewalId ())) {
            customerPolicyRenewalRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer policy renewal successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer policy renewal not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerPolicyRenewalRepository.existsById (id)) {
            customerPolicyRenewalRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer policy renewal removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer policy renewal not found"
            );
        }
    }
}
