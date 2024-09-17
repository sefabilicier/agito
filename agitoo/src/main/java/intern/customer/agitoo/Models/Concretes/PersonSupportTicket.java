package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.Priority;
import intern.customer.agitoo.Models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONSUPPORTTICKETS")
@Builder
public class PersonSupportTicket {

    @Id
    @GeneratedValue(generator = "person_support_ticket_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_support_ticket_sequence", sequenceName = "person_support_ticket_sequence", allocationSize = 1)
    @Column(name = "TICKETID")
    private Long ticketID;

    @Column(name = "TICKETNUMBER", unique = true)
    private String ticketNumber;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private Priority priority;

    @Column(name = "CREATEDDATE")
    private Date createdDate;

    @Column(name = "RESOLVEDDATE")
    private Date resolvedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONID")
    private Person person;

}
