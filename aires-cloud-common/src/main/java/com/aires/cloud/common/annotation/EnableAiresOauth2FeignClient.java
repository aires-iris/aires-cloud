package com.aires.cloud.common.annotation;

import com.aires.cloud.common.config.AiresOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AiresOAuth2FeignConfigure.class)
public @interface EnableAiresOauth2FeignClient {
}
