package intern.customer.agitoo.Core.Results;

import intern.customer.agitoo.Helper.Messages;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorResult extends Result {

    public ErrorResult (boolean success) {
        super (false);
    }

    public ErrorResult (boolean success, Messages message) {
        super (false, message);
    }
}
