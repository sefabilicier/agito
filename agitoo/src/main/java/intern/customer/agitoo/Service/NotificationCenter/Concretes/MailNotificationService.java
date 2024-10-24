package intern.customer.agitoo.Service.NotificationCenter.Concretes;

import intern.customer.agitoo.Service.NotificationCenter.Abstract.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationService implements NotificationService {


    private final JavaMailSender emailSender;


    public MailNotificationService (JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public String sendNotification (String recipient, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage ();
        mailMessage.setTo (recipient);
        mailMessage.setSubject ("Account Created");
        mailMessage.setFrom ("agitoo@noreply.com");
        mailMessage.setCc ("sefabilicier@gmail.com");
        mailMessage.setText (message);

        emailSender.send (mailMessage);

        return recipient;
    }
}
