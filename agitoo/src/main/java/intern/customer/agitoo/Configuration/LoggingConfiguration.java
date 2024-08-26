package intern.customer.agitoo.Configuration;

import intern.customer.agitoo.AgitooApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

    private static final Logger logger =
            LoggerFactory.getLogger (AgitooApplication.class);
}
