package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
}

