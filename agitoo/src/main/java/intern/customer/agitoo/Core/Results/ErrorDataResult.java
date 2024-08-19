package intern.customer.agitoo.Core.Results;

import intern.customer.agitoo.Helper.Messages;

public class ErrorDataResult<T> extends DataResult<T> {

    public ErrorDataResult (T data, boolean success, Messages message) {
        super (data, false, message);
    }

    public ErrorDataResult (T data) {
        super (data, false);
    }

    public ErrorDataResult (Messages message) {
        super (null, false, message);
    }

    public ErrorDataResult () {
        super (null, false);
    }
}
