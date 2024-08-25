package intern.customer.agitoo.Common.Results;

import intern.customer.agitoo.Helper.Messages;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataResult<T> extends Result {

    private T data;

    public DataResult (T data, boolean success, Messages message) {
        super (success, message);
        this.data = data;
    }

    public DataResult (T data, boolean success) {
        super (success);
        this.data = data;
    }

}
