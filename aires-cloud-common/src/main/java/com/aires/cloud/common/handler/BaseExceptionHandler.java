package com.aires.cloud.common.handler;

import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.exception.AiresAuthException;
import com.aires.cloud.common.exception.AiresException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:16
 */
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AiresResponse handleException(Exception e) {
        log.info(e.getMessage());
        return new AiresResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = AiresAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AiresResponse handleAiresAuthException(AiresAuthException e) {
        return new AiresResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = InvalidGrantException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AiresResponse handleAiresInvalidGrantException(AiresAuthException e) {
        return new AiresResponse().message(e.getMessage());
    }


    @ExceptionHandler(value = AiresException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AiresResponse handleAiresException(AiresException e) {
        log.error("系统错误", e);
        return new AiresResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AiresResponse handleAccessDeniedException(){

        return new AiresResponse().message("没有权限访问该资源");
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AiresResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new AiresResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AiresResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new AiresResponse().message(message.toString());
    }
}