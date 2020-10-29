package com.aires.cloud.common.validator;

import com.aires.cloud.common.annotation.IsMobile;
import com.aires.cloud.common.entity.RegexpConstant;
import com.aires.cloud.common.utils.AiresUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return AiresUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}