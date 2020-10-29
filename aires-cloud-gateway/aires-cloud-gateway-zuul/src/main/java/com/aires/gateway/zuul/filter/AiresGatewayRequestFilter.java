package com.aires.gateway.zuul.filter;

import com.aires.cloud.common.constant.AiresConstant;
import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.utils.AiresUtil;
import com.aires.gateway.zuul.properties.AiresGatewayProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-project
 * @description: 所有网关请求的过滤器,在请求转发到具体的微服务之前往header里写入信息
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:37
 */
@Slf4j
@Component
public class AiresGatewayRequestFilter extends ZuulFilter {
    @Autowired
    private AiresGatewayProperties properties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);
        // 禁止外部访问资源实现
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }

        if (!shouldForward) {
            HttpServletResponse response = ctx.getResponse();
            AiresResponse airesResponse = new AiresResponse().message("该URI不允许外部访问");
            try {

                AiresUtil.makeResponse(
                        response, "UTF-8",
                        HttpServletResponse.SC_FORBIDDEN, airesResponse
                );
                ctx.setSendZuulResponse(false);
                ctx.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        byte[] token = Base64Utils.encode((AiresConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(AiresConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}