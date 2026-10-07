package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StreamsTest {

	private final Streams streams = new Streams();

	private final Object[] anArray = new Object[]{
			new String("abc"),
			(short) 507,
			new StringBuilder("building...")};

	@Test
	void generatesAListOfToStringValues() {
		List<String> result = streams.streamStringValueToList(anArray);
		Assertions.assertThat(result).containsExactly("abc", "507", "building...");
	}

	@Test
	void generatesAListOfClassAndToStringValues() {
		List<String> result = streams.streamClassPlusStringValueToList(anArray);
		Assertions.assertThat(result).containsExactly("String : abc", "Short : 507", "StringBuilder : building...");
	}
}
