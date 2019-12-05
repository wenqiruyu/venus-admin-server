package com.server.venus.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.server.venus.annotation.LogAnnotation;
import com.server.venus.entity.Log;
import com.server.venus.entity.UserDetailsImpl;
import com.server.venus.enums.ResultEnum;
import com.server.venus.service.IVenusLogService;
import com.server.venus.utils.IpUtils;
import com.server.venus.utils.RequestPramsUtils;
import com.server.venus.utils.TokenUtils;
import com.server.venus.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * 项目名称：venus-admin-server
 * 类名称：LogAspect
 * 类描述：日志管理的切面类
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
@Aspect
@Component
@Order(30)
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private IVenusLogService venusLogService;

    private Log log = new Log();

    @Pointcut("@annotation(com.server.venus.annotation.LogAnnotation)")
    public void controllerAspect() {
    }

    /**
     * 在有LogAnnotation注解的接口执行前执行这个doBefore()方法
     *
     * @param joinPoint
     * @return void
     * @author yingx
     * @date 2019/11/27
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            // 操作用户名 用户的登陆注册没有token
            JSONObject params = getParams(joinPoint);
            String username = params.getString("username");
            // authentication存在则为登录成功过程
            Authentication authentication = (Authentication) params.get("authentication");
            if (StringUtils.isNotBlank(token)) {
                username = TokenUtils.getUsernameByToken(token);
            }
            if (StringUtils.isBlank(username) && authentication != null) {
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                username = userDetails.getUsername();
            }
            String ip = IpUtils.getIpAddr(request);
            // 方法名称
            String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
            // 日志名称
            String desc = getControllerMethodDescription(joinPoint);
            // 用户操作的Api
            String requestURI = request.getRequestURI();
            // 请求方式
            String method = request.getMethod();
            log.setLogName(desc);
            log.setUserIp(ip);
            log.setUserName(username);
            log.setMethodName(methodName);
            log.setApi(requestURI);
            log.setRequestType(method);
            log.setExceptionMsg("无异常");
            log.setIsEnable(1);
            log.setCreateBy(username);
            log.setLastUpdateBy(username);
        } catch (ClassNotFoundException e) {
            logger.error("LogAspect doBefore error ...", e);
        }
    }

    /**
     * 处理返回内容的入库
     *
     * @param ret 返回结果
     * @return void
     * @author yingx
     * @date 2019/11/27
     */
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) throws Throwable {

        // 处理完请求，返回内容
        ResultVO result = (ResultVO) ret;
        if (result != null) {
            // 保存返回结果
            log.setMessage(JSON.toJSONString(ret));
            // 异常原因
            if (result.getCode() != ResultEnum.SUCCESS.getCode()) {
                log.setIsEnable(3);
                log.setExceptionMsg(result.getMsg());
            }
        } else {
            if (StringUtils.isBlank(log.getUserName())) {
                log.setUserName("venus");
                log.setCreateBy("venus");
                log.setLastUpdateBy("venus");
            }
            // 处理登录情况
            if ("/venus-admin-server/user/login".equals(log.getApi()) && "用户登录失败".equals(log.getLogName())) {
                log.setLogName("用户登陆");
                log.setIsEnable(2);
                log.setMessage(ResultEnum.USER_NEED_AUTHORITIES.getMsg());
            }
        }
        //保存日志
        venusLogService.addVenusLog(log);
        logger.error("LogAspect addVenusLog end ...log:{}", log);
    }

    /**
     * 获取controller中的注解的日志名称
     *
     * @param joinPoint
     * @return java.lang.String
     * @author yingx
     * @date 2019/11/27
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();// 目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(LogAnnotation.class).value();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取请求参数，并返回JSON格式
     *
     * @param joinPoint
     * @return com.alibaba.fastjson.JSONObject
     * @author yingx
     * @date 2019/12/5
     */
    private static JSONObject getParams(JoinPoint joinPoint) {

        //获取参数值
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return null;
        }
        JSONObject params = new JSONObject();
        try {
            //对象接收参数
            String data = JSON.toJSONString(joinPoint.getArgs()[0]);
            params = JSON.parseObject(data);
        } catch (JSONException e) {
            //普通参数传入 获取参数名
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
                params.put(methodSignature.getParameterNames()[i], args[i]);
            }
        }
        return params;
    }
}
