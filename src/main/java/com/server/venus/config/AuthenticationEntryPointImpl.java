package com.server.venus.config;

import com.alibaba.fastjson.JSON;
import com.server.venus.enums.ResultEnum;
import com.server.venus.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest Request, HttpServletResponse Response, AuthenticationException e) throws IOException, ServletException {
        Response.getWriter().write(JSON.toJSONString(ResultVO.fail(ResultEnum.USER_NEED_AUTHORITIES)));
    }
}
