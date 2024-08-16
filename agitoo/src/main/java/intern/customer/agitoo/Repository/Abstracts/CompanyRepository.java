package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

