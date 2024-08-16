package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Repository.Abstracts.CustomerPaymentRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPaymentServiceImpl implements ICustomerPaymentService {

    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Override
    public DataResult<List<CustomerPayment>> getAll () {
        return new SuccessDataResult<List<CustomerPayment>> (
                customerPaymentRepository.findAll (),
                "Customer payments listed!"
        );
    }

    @Override
    public Result Add (CustomerPayment entity) {
        customerPaymentRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer payment added!"
        );
    }

    @Override
    public Result Update (CustomerPayment entity) {
        if (customerPaymentRepository.existsById (entity.getPaymentID ())) {
            customerPaymentRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer payment successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer payment not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerPaymentRepository.existsById (id)) {
            customerPaymentRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer payment removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer payment not found"
            );
        }
    }
}
