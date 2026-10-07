package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import java.util.Objects;

class Strings {

	boolean haveTheSameReference(final String s1, final String s2) {
		//noinspection StringEquality
		return s1 == s2;
	}

	boolean areEqual(final String s1, final String s2) {
		return Objects.equals(s1, s2);
	}
}
