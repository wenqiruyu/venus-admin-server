package com.server.venus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.venus.annotation.LogAnnotation;
import com.server.venus.enums.ResultEnum;
import com.server.venus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：venus
 * 类名称：LogoutSuccessHandlerImpl
 * 类描述：用户未登录时返回给前端的数据
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @LogAnnotation("用户登录失败")
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new ResultVO<>("login",
                ResultEnum.UNKNOWN_USER.getCode(), ResultEnum.UNKNOWN_USER.getMsg())));
    }

}
