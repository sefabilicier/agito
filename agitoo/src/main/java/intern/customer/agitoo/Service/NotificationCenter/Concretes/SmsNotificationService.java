package intern.customer.agitoo.Service.NotificationCenter.Concretes;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import intern.customer.agitoo.Service.NotificationCenter.Abstract.NotificationService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@PropertySource("classpath:Notification.properties")
public class SmsNotificationService implements NotificationService {


    @Value("${TWILIO.ACCOUNT.SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO.AUTH.TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO.OUTGOING.SMS.NUMBER}")
    String OUTGOING_SMS_NUMBER;

    @PostConstruct
    public void setup () {
        Twilio.init (ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public String sendNotification (String recipient, String message) {
        Message messageTwilio = Message.creator (
                new PhoneNumber (recipient), // the number receiver
                new PhoneNumber (OUTGOING_SMS_NUMBER), // the number sent
                message // message description
        ).create ();

        String messageId = messageTwilio.getSid ();
        log.info ("Message sent with ID: {}", messageId);
        return messageTwilio.getBody ();
    }

}