package intern.customer.agitoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "intern.customer.agitoo")
public class AgitooApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgitooApplication.class, args);
	}
}
