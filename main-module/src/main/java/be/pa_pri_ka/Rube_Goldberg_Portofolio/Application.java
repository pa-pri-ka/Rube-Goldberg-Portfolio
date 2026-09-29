package be.pa_pri_ka.Rube_Goldberg_Portofolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(final String[] args) {
		System.out.println(">>> Starting the Rube Goldberg Machine !");
		SpringApplication.run(Application.class, args);
		System.out.println(">>> Stopping the Rube Goldberg Machine... Bye!");
	}
}
