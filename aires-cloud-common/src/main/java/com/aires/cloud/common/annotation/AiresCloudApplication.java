package com.aires.cloud.common.annotation;

import com.aires.cloud.common.selector.AiresCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 组合注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AiresCloudApplicationSelector.class)
public @interface AiresCloudApplication {
}
