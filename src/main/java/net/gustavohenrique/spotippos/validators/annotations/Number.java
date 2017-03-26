package net.gustavohenrique.spotippos.validators.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Number {

	public int min() default Integer.MIN_VALUE;
	public int max() default Integer.MAX_VALUE;

}
