package be.pa_pri_ka.Rube_Goldberg_Portofolio.java;

class PatternMatching {

	String getFunnyCommentOnObject(Object object) {
		if (object instanceof String match) {
			return "Clearly a String: " + match;
		} else if (object instanceof Integer match) {
			return "It's an Integer, how nice: " + match;
		} else if (object instanceof Double match) {
			return "Oh this one has decimals after its integer part: " + match;
		}
		return "I don't know this one in particular: " + object.getClass().getSimpleName();
	}

	String getRange(Number number) {
		return switch (number) {
			case Integer n -> "This integer is between " + (n - 1) + " and " + (n + 1);
			case Double n -> "This double is between " + (n - 1.0) + " and " + (n + 1.0);
			default -> "Listen, I need an Integer or a Double";
		};
	}
}
