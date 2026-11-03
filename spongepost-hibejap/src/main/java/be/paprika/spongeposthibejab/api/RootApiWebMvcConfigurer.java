package be.paprika.spongeposthibejab.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @noinspection HardcodedFileSeparator
 */
@Configuration
public class RootApiWebMvcConfigurer implements WebMvcConfigurer {

	private static final long MAX_AGE = 3600L;

	@Value("${web.server}")
	private String webServer;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		allowCorsOnGetFor(registry,
				"/openapi.yaml",
				"/swagger-ui.html",
				"/api/hi",
				"/api/openapi/**",
				"/v3/api-docs");
	}

	private void allowCorsOnGetFor(CorsRegistry registry, String... paths) {
		Arrays.stream(paths).forEach(path -> registry.addMapping(path)
				.allowedOrigins(this.webServer)
				.allowedMethods("GET")
				.maxAge(MAX_AGE));
	}
}

