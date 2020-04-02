package com.example.JavaBot.Entity;

import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CapitalsInfoValidator implements Validator {

    public static final int CAPITAL_STRING_MIN_SIZE = 2;
    public static final int CAPITAL_STRING_MAX_SIZE = 255;


    @Override
    public boolean supports(Class<?> aClass) {
        return CapitalsInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name", "name is empty");
        ValidationUtils.rejectIfEmpty(errors,"description", "description is empty");

        CapitalsInfo capitalsInfo = (CapitalsInfo) o;

        if(StringUtils.hasLength(capitalsInfo.getName())
        && (capitalsInfo.getName().length() > CAPITAL_STRING_MAX_SIZE)
        || (capitalsInfo.getName().length() < CAPITAL_STRING_MIN_SIZE)) {
            errors.reject("Capital's name have to be longer than 2 and shorter then 255");
        }

        if(StringUtils.hasLength(capitalsInfo.getDescription())
                && (capitalsInfo.getDescription().length() > CAPITAL_STRING_MAX_SIZE)
                || (capitalsInfo.getDescription().length() < CAPITAL_STRING_MIN_SIZE)) {
            errors.reject("Capital's description have to be longer than 2 and shorter then 255");
        }

    }
}
