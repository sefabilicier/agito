package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.PersonActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonActivityRepository extends JpaRepository<PersonActivity, Long> {
}
