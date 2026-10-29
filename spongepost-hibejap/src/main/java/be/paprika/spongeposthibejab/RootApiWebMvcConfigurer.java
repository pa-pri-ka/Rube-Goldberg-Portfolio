package be.paprika.spongeposthibejab;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @noinspection HardcodedFileSeparator
 */
@Configuration
public class RootApiWebMvcConfigurer implements WebMvcConfigurer {

	private static final long MAX_AGE = 3600L;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/hi/**")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("GET")
				.maxAge(MAX_AGE);
	}
}

