package intern.customer.agitoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "intern.customer.agitoo")
@EnableJpaRepositories(basePackages = "intern.customer.agitoo.Repository.Abstracts")
@EntityScan(basePackages = "intern.customer.agitoo.Models.Concretes")
public class AgitooApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgitooApplication.class, args);
	}
}
