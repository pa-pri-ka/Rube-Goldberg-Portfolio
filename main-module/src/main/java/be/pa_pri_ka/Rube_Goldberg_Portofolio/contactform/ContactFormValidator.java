package be.pa_pri_ka.Rube_Goldberg_Portofolio.contactform;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ContactFormValidator implements Validator {
	@Override
	public boolean supports(final Class<?> clazz) {
		return ContactForm.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "required");
		final ContactForm contactForm = (ContactForm) target;
		if (null != contactForm.getFirstName() && contactForm.getFirstName().equals(contactForm.getLastName())) {
			errors.rejectValue("lastName", "equal.firstName");
		}
	}

}
