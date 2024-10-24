package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Common.Utilities.TicketNumberGenerator;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.Priority;
import intern.customer.agitoo.Models.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonSupportTicketDTO {

    private Long ticketID;

    @NotBlank(message = "{ticketNumber.notBlank}")
    private String ticketNumber;

    @NotBlank(message = "{subject.notBlank}")
    private String subject;

    @NotBlank(message = "{supportDescription.notBlank}")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{status.notNull}")
    private Status status;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{priority.notNull}")
    private Priority priority;

    @PastOrPresent(message = "{createdDate.pastOrPresent}")
    private Date createdDate;

    @FutureOrPresent(message = "{resolvedDate.futureOrPresent}")
    private Date resolvedDate;

    @JsonIgnore
    private Person person;
    private Long personId;

    public String getTicketNumber () {
        // Eğer ticketNumber null ise, dinamik olarak oluştur
        if (ticketNumber == null) {
            ticketNumber = "AGTIC - " + TicketNumberGenerator.generateRandomPart ();
        }
        return ticketNumber;
    }
}
