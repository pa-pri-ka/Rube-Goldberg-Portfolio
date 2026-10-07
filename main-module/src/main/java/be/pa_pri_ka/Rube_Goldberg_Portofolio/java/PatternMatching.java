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
}
