package intern.customer.agitoo.Core.Results;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {

    @Getter
    private boolean success;
    private String message;

    public Result (boolean success) {
        this.success = success;
    }

    public Result (boolean success, String message) {
        this (success);
        this.message = message;
    }
}
