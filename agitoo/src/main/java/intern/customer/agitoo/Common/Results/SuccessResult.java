package intern.customer.agitoo.Common.Results;

import intern.customer.agitoo.Helper.Messages;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SuccessResult extends Result {


    public SuccessResult (boolean success) {
        super (true);
    }

    public SuccessResult (boolean success, Messages message) {
        super (true, message);
    }


    public SuccessResult (Messages message, boolean success) {
        super (true, message);
    }
}
