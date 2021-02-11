package br.com.zup.projetopropostacartao.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {

    String message() default "O documento deve ter um formato válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}