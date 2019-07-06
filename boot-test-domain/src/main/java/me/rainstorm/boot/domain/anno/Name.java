package me.rainstorm.boot.domain.anno;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.SimpleDateFormat;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Name.NameChecker.class})
public @interface Name {
    String message() default "名称不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String pattern() default "";

    class NameChecker implements ConstraintValidator<Name, String> {

        SimpleDateFormat dateFormat;

        @Override
        public void initialize(Name constraintAnnotation) {
            dateFormat = new SimpleDateFormat(constraintAnnotation.pattern());
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            try {
                dateFormat.parse(value);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }
}