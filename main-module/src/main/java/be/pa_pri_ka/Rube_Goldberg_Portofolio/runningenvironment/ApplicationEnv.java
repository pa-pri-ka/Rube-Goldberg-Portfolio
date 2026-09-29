package be.pa_pri_ka.Rube_Goldberg_Portofolio.runningenvironment;

import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
class ApplicationEnv {

	private final ConfigurableEnvironment environment;

	ApplicationEnv(final ConfigurableEnvironment environment) {
		this.environment = environment;
	}

	Map<String, String> getApplicationProperties() {
		final MutablePropertySources propertySources = this.environment.getPropertySources();
		final List<Map.Entry<String, String>> properties = propertySources.stream()
				.filter(propertySource -> propertySource.getName().contains(".properties]"))
				.filter(propertySource -> propertySource instanceof EnumerablePropertySource<?>)
				.flatMap(propertySource -> {
					final String[] propertyNames = ((EnumerablePropertySource<?>) propertySource).getPropertyNames();
					return Arrays.stream(propertyNames)
							.map(property -> Map.entry(property, Objects.requireNonNull(propertySource.getProperty(property)).toString()));
				})
				.toList();
		return properties.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
