package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {
}
