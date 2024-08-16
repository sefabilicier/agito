package intern.customer.agitoo.Repository.Abstracts;

import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonSupportTicketRepository extends JpaRepository<PersonSupportTicket, Long> {
}
