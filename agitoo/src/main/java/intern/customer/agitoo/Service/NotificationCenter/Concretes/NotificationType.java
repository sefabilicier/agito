package intern.customer.agitoo.Service.NotificationCenter.Concretes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {
    MAIL ("MAIL"),
    SMS ("SMS");

    private final String type;
}
