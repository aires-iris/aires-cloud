package com.aires.cloud.common.handler;

import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.utils.AiresUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 20:57
 */
public class AiresAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AiresResponse airesResponse = new AiresResponse();
        AiresUtil.makeResponse(
                httpServletResponse, "UTF-8",
                HttpServletResponse.SC_UNAUTHORIZED, airesResponse.message("token无效")
        );
    }
}