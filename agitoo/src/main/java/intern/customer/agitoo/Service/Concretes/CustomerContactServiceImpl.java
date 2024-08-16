package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import intern.customer.agitoo.Repository.Abstracts.CustomerContactRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContactServiceImpl implements ICustomerContactService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Override
    public DataResult<List<CustomerContact>> getAll () {
        return new SuccessDataResult<List<CustomerContact>> (
                customerContactRepository.findAll (),
                "Customer contact listed!"
        );
    }

    @Override
    public Result Add (CustomerContact entity) {
        customerContactRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer contact added!"
        );
    }

    @Override
    public Result Update (CustomerContact entity) {
        if (customerContactRepository.existsById (entity.getContactID ())) {
            customerContactRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer contact updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer contact not found!"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (customerContactRepository.existsById (id)) {
            customerContactRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer contact removed"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer contact not found!"
            );
        }
    }
}
