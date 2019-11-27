package com.server.venus.config;

import com.alibaba.fastjson.JSON;
import com.server.venus.enums.ResultEnum;
import com.server.venus.vo.ResultVO;
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

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.getWriter().write(JSON.toJSONString(ResultVO.fail(ResultEnum.USER_NO_ACCESS)));
    }
}
