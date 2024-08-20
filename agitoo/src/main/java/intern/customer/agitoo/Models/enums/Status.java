package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    Open ("Open"),
    Processing ("Processing"),
    Resolved ("Resolved"),
    Closed ("Closed");

    private final String status;
}
