package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Functional-style operations on collections
 */
class Streams {

	List<String> streamStringValueToList(Object[] array) {
		return Arrays.stream(array).map(Objects::toString).collect(Collectors.toList());
	}

	List<String> streamClassPlusStringValueToList(Object[] array) {
		return Arrays.stream(array)
				.map(item -> item.getClass().getSimpleName() + " : " + item)
				.collect(Collectors.toList());
	}
}
