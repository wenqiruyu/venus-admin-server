package com.server.venus.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.venus.annotation.LogAnnotation;
import com.server.venus.utils.Constants;
import com.server.venus.utils.TokenUtils;
import com.server.venus.vo.LoginUserVO;
import com.server.venus.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 项目名称：permission-demo
 * 类名称：CustomUsernamePasswordAuthenticationFilter
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/10
 * 修改人：yingx
 * 修改时间： 2019/10/10
 * 修改备注：
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /*public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {

        super.setFilterProcessesUrl("/user/login");
    }*/

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            UsernamePasswordAuthenticationToken authRequest = null;
            try {
                LoginUserVO loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUserVO.class);
                logger.info("CustomUsernamePasswordAuthenticationFilter attemptAuthentication ... loginUser:{}", loginUser);
                authRequest = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
                System.out.println(authRequest);
            } catch (IOException e) {
                logger.error("CustomUsernamePasswordAuthenticationFilter attemptAuthentication error ... ", e);
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    /*@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        UserDetails userDetail = (UserDetails) authResult.getPrincipal();
        String token = TokenUtils.getToken(userDetail.getUsername(), false);
        response.setHeader("token", Constants.TOKEN_PREFIX + token);
        logger.info("CustomUsernamePasswordAuthenticationFilter successfulAuthentication ... token:{}", token);
        response.getWriter().write(objectMapper.writeValueAsString(new ResultVO(Constants.TOKEN_PREFIX + token)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        logger.info("CustomUsernamePasswordAuthenticationFilter unsuccessfulAuthentication error ...");
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }*/
}
