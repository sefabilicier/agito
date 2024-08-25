package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.ActivityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONACTIVITIES")
@Builder
public class PersonActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITYID")
    private Long activityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVITYTYPE")
    private ActivityType activityType;

    @Column(name = "ACTIVITYDATE")
    private LocalDateTime activityDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONID")
    private Person person;
}
