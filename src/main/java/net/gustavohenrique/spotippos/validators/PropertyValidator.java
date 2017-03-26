package net.gustavohenrique.spotippos.validators;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.gustavohenrique.spotippos.exceptions.ValidationException;
import net.gustavohenrique.spotippos.validators.annotations.Number;
import net.gustavohenrique.spotippos.validators.annotations.Required;

@Component
public class PropertyValidator implements Validator {

	public void validate(Object obj) throws ValidationException {
		for(Field field  : obj.getClass().getDeclaredFields()) {
			validateRequiredAnnotation(field, obj);
			validateNumberAnnotation(field, obj);
		}
	}

	private void validateNumberAnnotation(Field field, Object obj) throws ValidationException {
		if (field.isAnnotationPresent(Number.class)) {
			try {
				field.setAccessible(true);
				int value = (int) field.get(obj);
				Number annotation = field.getAnnotation(Number.class);
				int min = annotation.min();
				int max = annotation.max();
				if (value < min || value > max) {
					throw new ValidationException("The value of [" + field.getName() + "] should be between " + min + " and " + max + ".");
				}
			} catch (Exception e) {
				throw new ValidationException(e.getMessage());
			}
		}
	}

	private void validateRequiredAnnotation(Field field, Object obj) throws ValidationException {
		if (field.isAnnotationPresent(Required.class)) {
			try {
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value == null || StringUtils.isEmpty(value)) {
					throw new ValidationException("The required field [" + field.getName() + "] is empty.");
				}
			} catch (Exception e) {
				throw new ValidationException(e.getMessage());
			}
		}
	}

}
