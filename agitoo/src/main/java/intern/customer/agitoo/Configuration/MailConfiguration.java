package intern.customer.agitoo.Service.NotificationCenter.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSender javaMailSender () {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl ();

        mailSender.setHost ("smtp.example.com"); // replace with your SMTP host
        mailSender.setPort (587); // replace with your SMTP port
        mailSender.setUsername ("your-email@example.com"); // replace with your email
        mailSender.setPassword ("your-password"); // replace with your email password

        Properties props = mailSender.getJavaMailProperties ();
        props.put ("mail.transport.protocol", "smtp");
        props.put ("mail.smtp.auth", "true");
        props.put ("mail.smtp.starttls.enable", "true");
        props.put ("mail.debug", "true");

        return mailSender;
    }
}
