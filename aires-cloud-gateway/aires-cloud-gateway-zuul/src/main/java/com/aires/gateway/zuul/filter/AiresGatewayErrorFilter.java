package com.aires.gateway.zuul.filter;

import com.aires.cloud.common.utils.AiresUtil;
import com.aires.cloud.common.entity.AiresResponse;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: cloud-project
 * @description: 自定义Zuul异常处理
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:12
 */
@Slf4j
@Component
public class AiresGatewayErrorFilter extends SendErrorFilter {
    @Override
    public Object run() {
        try {
            AiresResponse airesResponse = new AiresResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            airesResponse = resolveExceptionMessage(message, serviceId, airesResponse);

            HttpServletResponse response = ctx.getResponse();
            AiresUtil.makeResponse(
                    response, "UTF-8",
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, airesResponse
            );
            log.error("Zuul sendError：{}", airesResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private AiresResponse resolveExceptionMessage(String message, String serviceId, AiresResponse airesResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return airesResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return airesResponse.message(serviceId + "服务不可用");
        }
        return airesResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}