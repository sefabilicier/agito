package intern.customer.agitoo.Service.NotificationCenter;

import intern.customer.agitoo.Service.NotificationCenter.Abstract.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificationManager {

    @Autowired
    private List<NotificationService> notificationServices;

    public void notify (String recipient, String message /*NotificationType type*/) {
        for (NotificationService service : notificationServices) {
            service.sendNotification (recipient, message);
        }
        log.info ("Notification sent to {}: {}", recipient, message);
    }
}


