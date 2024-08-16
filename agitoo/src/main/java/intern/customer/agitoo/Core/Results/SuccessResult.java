package intern.customer.agitoo.Core.Results;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SuccessResult extends Result {


    public SuccessResult (boolean success) {
        super (true);
    }

    public SuccessResult (boolean success, String message) {
        super (true, message);
    }


    public SuccessResult (String message, boolean success) {
        super (true, message);
    }
}
