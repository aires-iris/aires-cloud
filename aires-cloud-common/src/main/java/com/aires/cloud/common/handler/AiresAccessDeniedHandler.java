package com.aires.cloud.common.handler;

import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.utils.AiresUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:03
 */
public class AiresAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AiresResponse airesResponse = new AiresResponse();
        AiresUtil.makeResponse(
                httpServletResponse, "UTF-8",
                HttpServletResponse.SC_FORBIDDEN, airesResponse.message("没有权限访问该资源"));
    }
}