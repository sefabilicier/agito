package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByFirstName (String name);
}
