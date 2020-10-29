package com.aires.cloud.common.selector;

import com.aires.cloud.common.config.AiresAuthExceptionConfigure;
import com.aires.cloud.common.config.AiresOAuth2FeignConfigure;
import com.aires.cloud.common.config.AiresServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: cloud-project
 * @description: 组合配置
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:52
 */
public class AiresCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                AiresAuthExceptionConfigure.class.getName(),
                AiresOAuth2FeignConfigure.class.getName(),
                AiresServerProtectConfigure.class.getName()
        };
    }
}