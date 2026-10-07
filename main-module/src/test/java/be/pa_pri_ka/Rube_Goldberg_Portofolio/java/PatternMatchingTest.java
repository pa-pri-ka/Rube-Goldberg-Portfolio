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

	@Test
	void providesFeedbackOfANumberRange() {
		assertEquals("This integer is between 122 and 124", patternMatching.getRange(123));
		assertEquals("This double is between 455.99 and 457.99", patternMatching.getRange(456.99));
		assertEquals("Listen, I need an Integer or a Double", patternMatching.getRange((short) 1));
	}
}
