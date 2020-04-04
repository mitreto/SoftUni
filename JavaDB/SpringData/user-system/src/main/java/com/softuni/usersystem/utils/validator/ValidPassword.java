package com.softuni.usersystem.utils.validator;

import org.dom4j.rule.Rule;
import org.hibernate.validator.constraints.ConstraintComposition;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    int minLength();
    int maxLength();
    boolean containsDigit();
    boolean containsLowercase();
    boolean containsUppercase();
    boolean containsSpecialSymbol();
    String message();



}
