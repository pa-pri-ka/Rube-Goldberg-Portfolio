package be.pa_pri_ka.Rube_Goldberg_Portofolio.random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class Config {

	@Bean
	@Scope("prototype")
	RandomBean randomBean() {
		return new RandomBean();
	}
}
