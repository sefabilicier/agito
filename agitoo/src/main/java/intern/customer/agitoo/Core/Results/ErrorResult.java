package intern.customer.agitoo.Core.Results;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorResult extends Result {

    public ErrorResult (boolean success) {
        super (false);
    }

    public ErrorResult (boolean success, String message) {
        super (false, message);
    }
}
