package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONID")
    private Long personId;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "MIDDLENAME", nullable = true)
    private String middleName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private PersonGender gender;

    @Column(name = "DATEOFBIRTH")
    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "MARITALSTATUS")
    private MaritalStatus maritalStatus;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "OCCUPATION")
    private String occupation;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "CUSTOMERID")
    private PersonActivity personActivity;


    @OneToMany(mappedBy = "person")
    List<PersonActivity> personActivities;

    @OneToMany(mappedBy = "person")
    List<PersonFeedback> personFeedbacks;

    @OneToMany(mappedBy = "person")
    List<PersonJobLife> personJobLifes;

    @OneToMany(mappedBy = "person")
    List<PersonSupportTicket> personSupportTickets;

}
