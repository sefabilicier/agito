package intern.customer.agitoo.Service.Rules;

import intern.customer.agitoo.Common.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommonBusinessRules {

    public static <T, ID> void checkIfIdExist (JpaRepository<T, ID> repository, ID id) {
        if (repository.existsById (id)) {
            repository.deleteById (id);
        } else {
            throw new BusinessException ("Id not exist");
        }
    }

}