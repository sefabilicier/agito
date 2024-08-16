package intern.customer.agitoo.Core.Results;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DataResult<T> extends Result {

    private T data;

    public DataResult (T data, boolean success, String message) {
        super (success, message);
        this.data = data;
    }

    public DataResult (T data, boolean success) {
        super (success);
        this.data = data;
    }

}
