package be.pa_pri_ka.Rube_Goldberg_Portofolio.purejava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

	private final Strings strings = new Strings();

	@Test
	void checksIfTwoStringsAreTheSameInThePool() {
		String s1 = "Hello World";
		String s2 = "Hello World";

		assertTrue(strings.haveTheSameReference(s1, s2));
		assertTrue(strings.areEqual(s1, s2));

		//noinspection StringOperationCanBeSimplified
		String s3 = new String("Hello World");

		assertFalse(strings.haveTheSameReference(s1, s3));
		assertTrue(strings.areEqual(s1, s3));

		String s3Intern = s3.intern();

		assertTrue(strings.haveTheSameReference(s1, s3Intern));
		assertTrue(strings.areEqual(s1, s3Intern));

		//noinspection StringBufferReplaceableByString
		final String buildString = new StringBuilder("Hello World").toString();
		assertFalse(strings.haveTheSameReference(s1, buildString));
		assertTrue(strings.areEqual(s1, buildString));
		assertTrue(strings.haveTheSameReference(s2, buildString.intern()));
	}
}
