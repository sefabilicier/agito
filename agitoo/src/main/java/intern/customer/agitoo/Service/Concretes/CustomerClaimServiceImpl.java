package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import intern.customer.agitoo.Repository.Abstracts.CustomerClaimRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerClaimServiceImpl implements ICustomerClaimService {

    @Autowired
    private CustomerClaimRepository customerClaimRepository;

    @Override
    public DataResult<List<CustomerClaim>> getAll () {
        return new SuccessDataResult<List<CustomerClaim>> (
                customerClaimRepository.findAll (),
                "Customer claims listed!"
        );
    }

    @Override
    public Result Add (CustomerClaim entity) {
        customerClaimRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer claim added!"
        );
    }

    @Override
    public Result Update (CustomerClaim entity) {
        if (customerClaimRepository.existsById (entity.getClaimId ())) {
            customerClaimRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer claims added!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer claim not found!"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerClaimRepository.existsById (id)) {
            customerClaimRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer claim removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer claim not found!"
            );
        }
    }
}
