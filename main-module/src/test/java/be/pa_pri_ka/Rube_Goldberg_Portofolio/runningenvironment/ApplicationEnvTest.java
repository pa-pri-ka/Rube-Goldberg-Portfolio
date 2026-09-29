package be.pa_pri_ka.Rube_Goldberg_Portofolio.runningenvironment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationEnvTest {

	@Autowired
	private ApplicationEnv applicationEnv;

	@Test
	void getsApplicationProperties() {
		final Map<String, String> properties = this.applicationEnv.getApplicationProperties();
		assertThat(properties.containsKey("spring.application.name")).isTrue();
	}
}
