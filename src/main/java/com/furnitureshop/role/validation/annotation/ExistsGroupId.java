package com.furnitureshop.role.validation.annotation;
import com.furnitureshop.role.validation.validator.ExistsGroupIdValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistsGroupIdValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExistsGroupId {
	String message() default "Group doesn't exist.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
