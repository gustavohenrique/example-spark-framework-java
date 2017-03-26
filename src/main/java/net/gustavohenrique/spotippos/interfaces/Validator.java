package net.gustavohenrique.spotippos.interfaces;

import net.gustavohenrique.spotippos.exceptions.ValidationException;

public interface Validator {

	public void validate(Object obj) throws ValidationException;
}
