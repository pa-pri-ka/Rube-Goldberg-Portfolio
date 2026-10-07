package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import java.util.Objects;
import java.util.stream.Collectors;

class Strings {

	boolean haveTheSameReference(final String s1, final String s2) {
		//noinspection StringEquality
		return s1 == s2;
	}

	boolean areEqual(final String s1, final String s2) {
		return Objects.equals(s1, s2);
	}

	String repeatThrice(String text) {
		return text.lines().map(line -> {
					if (line.isBlank()) {
						return "404: could not find a non-blank line";
					}
					return "I'm gonna say this only thrice: " + line.strip().repeat(3);
				})
				.collect(Collectors.joining("|"));
	}
}
