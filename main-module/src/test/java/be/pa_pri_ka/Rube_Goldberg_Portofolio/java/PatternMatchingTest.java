package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternMatchingTest {

	private final PatternMatching patternMatching = new PatternMatching();

	@Test
	void providesAFunnyCommentOnObjectTypes() {
		String comment1 = patternMatching.getFunnyCommentOnObject("Hi there!");
		assertTrue(comment1.contains("String"));

		String comment2 = patternMatching.getFunnyCommentOnObject(12345);
		assertTrue(comment2.contains("Integer"));

		String comment3 = patternMatching.getFunnyCommentOnObject(3.1415);
		assertTrue(comment3.contains("decimals"));
	}
}
