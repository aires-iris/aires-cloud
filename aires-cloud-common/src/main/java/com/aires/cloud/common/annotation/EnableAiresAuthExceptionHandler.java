package com.aires.cloud.common.annotation;

import com.aires.cloud.common.config.AiresAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AiresAuthExceptionConfigure.class)
public @interface EnableAiresAuthExceptionHandler {
}