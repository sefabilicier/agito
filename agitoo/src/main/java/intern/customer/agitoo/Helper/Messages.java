package intern.customer.agitoo.Helper;

import lombok.Getter;

@Getter
public enum Messages {
    LISTED ("data successfully listed!"),
    ADDED ("successfully added"),
    REMOVED ("successfully removed"),
    UPDATED ("successfully updated"),
    NOT_FOUND ("not found"),
    ALREADY_EXISTS ("already exists"),
    ALREADY_DELETED ("already deleted"),
    ALREADY_UPDATED ("already updated");

    private final String text;

    Messages (String text) {
        this.text = text;
    }
}
