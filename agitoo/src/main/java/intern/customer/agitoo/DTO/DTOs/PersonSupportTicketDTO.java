package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonSupportTicketDTO {

    @NotNull
    private String ticketNumber;
    @NotBlank
    private String subject;
    @NotBlank
    private String description;
    @NotBlank
    private String status;

    private PersonDTO person;

    public String getTicketNumber(){
        return ticketNumber.substring (0,3) + " -************";
    }
}
