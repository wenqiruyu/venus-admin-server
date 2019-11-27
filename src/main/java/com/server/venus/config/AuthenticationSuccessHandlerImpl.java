package com.server.venus.config;

import com.alibaba.fastjson.JSON;
import com.server.venus.entity.UserDetailsImpl;
import com.server.venus.utils.TokenUtils;
import com.server.venus.vo.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = TokenUtils.getToken(userDetails.getUsername(), false);
        response.getWriter().write(JSON.toJSONString(ResultVO.success(jwtToken)));
    }
}
