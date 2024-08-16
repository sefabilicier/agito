package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {

    Service ("Service"),
    Product ("Product"),
    General ("General");

    private final String FeedbackType;
}
