package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerDebitCard;
import intern.customer.agitoo.Repository.Abstracts.CustomerDebitCardRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerDebitCardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDebitCardServiceImpl implements ICustomerDebitCardService {

    @Autowired
    private CustomerDebitCardRepository customerDebitCardRepository;

    @Override
    public DataResult<List<CustomerDebitCard>> getAll () {
        return new SuccessDataResult<List<CustomerDebitCard>> (
                customerDebitCardRepository.findAll (),
                "Customer debit cards listed!"
        );
    }

    @Override
    public Result Add (CustomerDebitCard entity) {
        customerDebitCardRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer debit card added!"
        );
    }

    @Override
    public Result Update (CustomerDebitCard entity) {
        if (customerDebitCardRepository.existsById (entity.getDebitCardID ())) {
            customerDebitCardRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer debit card updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer debit card updated"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerDebitCardRepository.existsById (id)) {
            customerDebitCardRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer debit card removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer debit card not found!"
            );
        }
    }
}
