package intern.customer.agitoo.Models.Concretes;

import intern.customer.agitoo.Models.enums.FeedbackType;
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
@Table(name = "PERSONFEEDBACKS")
@Builder
public class PersonFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACKID")
    private Long feedbackId;

    @Column(name = "FEEDBACKDATE")
    private LocalDateTime feedbackDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "FEEDBACKTYPE")
    private FeedbackType feedbackType;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONID")
    private Person person;

}
