package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonProperty;
import intern.customer.agitoo.Models.enums.Status;
import jakarta.validation.Valid;
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

    @NotBlank(message = "{ticketNumber.notBlank}")
    private String ticketNumber;

    @NotBlank(message = "{subject.notBlank}")
    private String subject;

    @NotBlank(message = "{supportDescription.notBlank}")
    private String description;

    @NotNull(message = "{status.notNull}")
    private Status status;

    @PastOrPresent(message = "{createdDate.pastOrPresent}")
    private Date createdDate;

    @FutureOrPresent(message = "{resolvedDate.futureOrPresent}")
    private Date resolvedDate;

    @Valid
    private PersonDTO person;

    @JsonProperty("maskedTicketNumber")
    public String getTicketNumber(){
        return ticketNumber.substring (0,3) + " -************";
    }
}
