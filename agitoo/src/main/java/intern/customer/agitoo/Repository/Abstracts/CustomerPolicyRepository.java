package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {
}
