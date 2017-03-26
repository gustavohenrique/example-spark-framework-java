package net.gustavohenrique.spotippos.validators;

import net.gustavohenrique.spotippos.exceptions.ValidationException;

public interface Validator {

	public void validate(Object obj) throws ValidationException;
}
