package intern.customer.agitoo.DTO.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonSupportTicketDTO {

    private String ticketNumber;
    private String subject;
    private String description;
    private String status;
}
