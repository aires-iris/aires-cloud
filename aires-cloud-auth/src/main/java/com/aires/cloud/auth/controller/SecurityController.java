package com.aires.cloud.auth.controller;

import com.aires.cloud.auth.service.ValidateCodeService;
import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.exception.AiresAuthException;
import com.aires.cloud.common.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public AiresResponse signout(HttpServletRequest request) throws AiresAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        AiresResponse response = new AiresResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new AiresAuthException("退出登录失败");
        }
        return response.message("退出登录成功");
    }
}
