package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {
}
