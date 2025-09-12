package ir.dc.userAuthenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UserAuthenticatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticatorApplication.class, args);
	}

}
