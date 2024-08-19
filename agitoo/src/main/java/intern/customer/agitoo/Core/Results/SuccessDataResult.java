package intern.customer.agitoo.Core.Results;


import intern.customer.agitoo.Helper.Messages;

public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult (T data, Messages message) {
        super (data, true, message);
    }


    public SuccessDataResult (boolean success, T data) {
        super (data, true);
    }

    public SuccessDataResult () {
        super (null, true);
    }

    public SuccessDataResult (Messages message) {
        super (null, true, message);
    }
}
