package com.esrichina.geoservices.validator;

import com.esrichina.geoservices.annotation.StateAnnotation;
import com.esrichina.geoservices.util.DictConvertUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class StateValueValidator implements ConstraintValidator<StateAnnotation, String> {

    volatile String identify = "";

    @Override
    public void initialize(StateAnnotation constraintAnnotation) {
        identify = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        List<DictConvertUtil.Dict> list = DictConvertUtil.getAllDict();
        List<DictConvertUtil.Dict> repeat = list.stream().filter(item -> item.getDictValue().equals(value) && item.getDictType().equals(identify)).collect(Collectors.toList());
        if (repeat.size() == 0) return false;
        return true;
    }
}