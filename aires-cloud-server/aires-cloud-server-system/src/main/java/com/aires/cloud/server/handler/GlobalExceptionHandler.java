package com.aires.cloud.server.handler;

import com.aires.cloud.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: cloud-project
 * @description: 对于通用的异常类型捕获可以在BaseExceptionHandler中定义，而当前微服务系统独有的异常类型捕获可以在GlobalExceptionHandler中定义。
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:17
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}