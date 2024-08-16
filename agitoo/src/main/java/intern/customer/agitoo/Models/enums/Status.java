package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    Open ("Open"),
    InProgress ("In Progress"),
    Resolved ("Resolved"),
    Closed ("Closed");

    private final String Status;
}
