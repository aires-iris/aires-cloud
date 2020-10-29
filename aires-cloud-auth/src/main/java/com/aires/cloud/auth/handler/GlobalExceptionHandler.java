package com.aires.cloud.auth.handler;

import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.handler.BaseExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: cloud-project
 * @description: 对于通用的异常类型捕获可以在BaseExceptionHandler中定义，而当前微服务系统独有的异常类型捕获可以在GlobalExceptionHandler中定义。
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:17
 */
@RestControllerAdvice
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AiresResponse handleMethodNotSupportedException(Exception e, HttpServletRequest request) {
        return new AiresResponse().message("不支持的HTTP请求方法!  "+request.getMethod());
    }
}