package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.CustomerDebitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDebitCardRepository extends JpaRepository<CustomerDebitCard, Long> {

}
