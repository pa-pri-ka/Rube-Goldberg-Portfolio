package be.pa_pri_ka.Rube_Goldberg_Portofolio.contactform;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.SimpleErrors;
import org.springframework.validation.ValidationUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactFormValidatorTest {

	@Test
	void validatesAContactFormsFirstName() {
		final ContactForm form = new ContactForm(null, null, "abc@test.com", "my message");

		final Errors errors = new SimpleErrors(form);
		ValidationUtils.invokeValidator(new ContactFormValidator(), form, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		final FieldError firstName = errors.getFieldError("firstName");
		assertNotNull(firstName);
		assertEquals("required", firstName.getCode());
	}

	@Test
	void validatesAContactFormsEmail() {
		final ContactForm form = new ContactForm("first name", null, "", "my message");

		final Errors errors = new SimpleErrors(form);
		ValidationUtils.invokeValidator(new ContactFormValidator(), form, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		final FieldError error = errors.getFieldError("email");
		assertNotNull(error);
		assertEquals("required", error.getCode());
	}

	@Test
	void validatesAContactFormsMessage() {
		final ContactForm form = new ContactForm("first name", null, "tralala", null);

		final Errors errors = new SimpleErrors(form);
		ValidationUtils.invokeValidator(new ContactFormValidator(), form, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		final FieldError error = errors.getFieldError("message");
		assertNotNull(error);
		assertEquals("required", error.getCode());
	}

	@Test
	void validatesAContactFormsLastNameDifferenceFromFirstName() {
		final ContactForm form = new ContactForm("first name", "first name", "tralala", "my message");

		final Errors errors = new SimpleErrors(form);
		ValidationUtils.invokeValidator(new ContactFormValidator(), form, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		final FieldError error = errors.getFieldError("lastName");
		assertNotNull(error);
		assertEquals("equal.firstName", error.getCode());
	}
}
