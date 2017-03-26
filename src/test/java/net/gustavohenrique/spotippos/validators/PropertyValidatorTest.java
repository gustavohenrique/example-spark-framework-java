package net.gustavohenrique.spotippos.validators;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;
import net.gustavohenrique.spotippos.exceptions.ValidationException;
import net.gustavohenrique.spotippos.models.Property;

public class PropertyValidatorTest extends TestCase {

	private Validator validator = new PropertyValidator();

	@Test(expected=ValidationException.class)
	public void testRequiredFieldsAreEmpty() {
		Property property = new Property();
		try {
			validator.validate(property);
		}
		catch (ValidationException e) {}
	}
	
	@Test
	public void testRequiredFieldsAreFill() throws ValidationException {
		Property property = fake();
		validator.validate(property);
	}
	
	@Test(expected=ValidationException.class)
	public void testNumberFieldWithLowMinValue() {
		Property property = fake();
		property.beds = 0;
		try {
			validator.validate(property);
		}
		catch (ValidationException e) {}
	}
	
	@Test(expected=ValidationException.class)
	public void testNumberFieldWithHighMaxValue() {
		Property property = fake();
		property.beds = 20;
		try {
			validator.validate(property);
		}
		catch (ValidationException e) {}
	}
	
	@Test
	public void testNumberFieldWithValidValue() {
		Property property = fake();
		property.beds = 3;
		try {
			validator.validate(property);
		}
		catch (ValidationException e) {
			fail();
		}
	}

	private Property fake() {
		Property property = new Property();
		property.title = "Title";
		property.description = "Some description";
		property.price = new BigDecimal("1000000");
		property.baths = 3;
		property.beds = 2;
		property.squareMeters = 80;
		return property;
	}
}
