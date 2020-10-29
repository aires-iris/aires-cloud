package com.aires.cloud.common.annotation;

import com.aires.cloud.common.config.AiresServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AiresServerProtectConfigure.class)
public @interface EnableAiresServerProtect {
}
