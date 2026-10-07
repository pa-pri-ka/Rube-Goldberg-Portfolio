package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

class LambdasTest {

	private final Lambdas lambdas = new Lambdas();

	private final List<?> aList = Lists.newArrayList(
			new String("abc"),
			(short) 507,
			new StringBuilder("building..."));

	@Test
	void generatesAListOfToStringValues() {
		List<String> result = lambdas.listToString(aList);
		Assertions.assertThat(result).containsExactly("abc", "507", "building...");
	}

	@Test
	void generatesAListOfClassAndToStringValues() {
		List<String> result = lambdas.listToClassAndString(aList);
		Assertions.assertThat(result).containsExactly("String : abc", "Short : 507", "StringBuilder : building...");
	}
}
