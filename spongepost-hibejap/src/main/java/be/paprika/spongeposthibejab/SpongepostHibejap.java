package be.paprika.spongeposthibejab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpongepostHibejap {

	public static void main(String[] args) {
		SpringApplication.run(SpongepostHibejap.class, args);
		// Just to ensure the env entries are present
		Set<Map.Entry<String, String>> tmdbKeys = System.getenv().entrySet().stream()
				.filter(entry -> entry.getKey().toUpperCase(Locale.ROOT).startsWith("TMDB"))
				.collect(Collectors.toSet());
		if (tmdbKeys.isEmpty()) {
			System.out.println("No TMDB keys found !!!");
		} else {
			System.out.println(tmdbKeys.size() + " differently-named TMDB-related keys were found.");
		}
	}
}
