package com.aires.cloud.common.interceptor;

import com.aires.cloud.common.constant.AiresConstant;
import com.aires.cloud.common.entity.AiresResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-project
 * @description: 所有微服务需要用到的全局拦截起，校验请求是否来的网关
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:41
 */
public class AiresServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(AiresConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(AiresConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            AiresResponse airesResponse = new AiresResponse();
            response.setContentType("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(airesResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}