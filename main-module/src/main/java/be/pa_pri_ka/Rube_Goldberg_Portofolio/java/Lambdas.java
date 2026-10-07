package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Behavior as argument
 */
class Lambdas {
	
	List<String> listToString(final List<?> aList) {
		List<String> result = new ArrayList<>();
		aList.forEach(item -> result.add(item.toString()));
		return result;
	}

	List<String> listToClassAndString(final List<?> aList) {
		List<String> result = new ArrayList<>();
		aList.forEach(item -> result.add(item.getClass().getSimpleName() + " : " + item));
		return result;
	}
}
