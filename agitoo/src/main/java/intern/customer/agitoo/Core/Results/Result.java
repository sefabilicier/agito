package intern.customer.agitoo.Core.Results;

import intern.customer.agitoo.Helper.Messages;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {


    private boolean success;
    private Messages message;

    public Result (boolean success) {
        this.success = success;
    }

    public Result (boolean success, Messages message) {
        this (success);
        this.message = message;
    }
}
