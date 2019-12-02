package com.server.venus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.venus.annotation.LogAnnotation;
import com.server.venus.enums.ResultEnum;
import com.server.venus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：venus
 * 类名称：LogoutSuccessHandlerImpl
 * 类描述：无权访问
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @LogAnnotation("用户登录失败")
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new ResultVO<>("login",
                ResultEnum.INVALID_ACCESS.getCode(), ResultEnum.INVALID_ACCESS.getMsg())));
    }
}
