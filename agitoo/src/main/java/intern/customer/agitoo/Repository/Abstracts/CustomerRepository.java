package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
