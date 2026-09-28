package be.pa_pri_ka.Rube_Goldberg_Portofolio.contactform;

class ContactForm {

	private final String firstName;
	private final String lastName;
	private final String email;
	private final String message;

	ContactForm(final String firstName, final String lastName, final String email, final String message) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.message = message;
	}

	String getFirstName() {
		return this.firstName;
	}

	String getLastName() {
		return this.lastName;
	}
}
