package com.server.venus.exception;

import com.server.venus.enums.ExceptionEnum;
import com.server.venus.vo.ErrorDataVO;
import com.server.venus.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

/**
 * 项目名称：venus-admin-server
 * 类名称：ExceptionResolver
 * 类描述：统一处理 Controller 的异常
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
@RestControllerAdvice
public class ExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    private String domain = "venus";

    /**
     * 针对业务异常的处理
     *
     * @param exception 业务异常
     * @param request   http request
     * @param response  http response
     * @return 异常处理结果
     */
    @ExceptionHandler(value = ExtenException.class)
    @SuppressWarnings("unchecked")
    public ResultVO extensionException(ExtenException exception, HttpServletRequest request, HttpServletResponse response) {

        logger.info("请求发生异常，url:{}, message:{}", request.getRequestURL().toString(), exception.getMessage());
        ResultVO resultVO = new ResultVO();
        resultVO.setDomain(StringUtils.isEmpty(exception.getDomain()) ? domain : exception.getDomain());
        resultVO.setCode(exception.getCode());
        resultVO.setMsg(exception.getMessage());
        if (Objects.nonNull(exception.getData()) && exception.getData() instanceof List) {
            if (((List) exception.getData()).size() > 0 && (((List) exception.getData()).get(0) instanceof ErrorDataVO)) {
                resultVO.setErrors((List<ErrorDataVO>) exception.getData());
            }
        }
        return resultVO;
    }

    /**
     * 针对参数校验失败异常的处理
     *
     * @param exception 参数校验异常
     * @param request   http request
     * @param response  http response
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResultVO databindException(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        logger.info("请求发生异常，url:{}, message:{}, 详情:{}", request.getRequestURL().toString(), exception.getMessage(), exception);
        ResultVO resultVO = new ResultVO();
        resultVO.setDomain(domain);
        resultVO.setCode(ExceptionEnum.PARAM_VALIDATED_UN_PASS.getCode());
        resultVO.setMsg(ExceptionEnum.PARAM_VALIDATED_UN_PASS.getMessage());
        if (exception instanceof BindException) {
            for (FieldError fieldError : ((BindException) exception).getBindingResult().getFieldErrors()) {
                resultVO.addError(fieldError.getField(), fieldError.getDefaultMessage());
            }
        } else if (exception instanceof MethodArgumentNotValidException) {
            for (FieldError fieldError : ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()) {
                resultVO.addError(fieldError.getField(), fieldError.getDefaultMessage());
            }
        } else if (exception instanceof ConstraintViolationException) {
            for (ConstraintViolation cv : ((ConstraintViolationException) exception).getConstraintViolations()) {
                resultVO.addError(cv.getPropertyPath().toString(), cv.getMessage());
            }
        }
        return resultVO;
    }

    /**
     * 针对spring web 中的异常的处理
     *
     * @param exception Spring Web 异常
     * @param request   http request
     * @param response  http response
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class
    })
    public ResultVO springWebExceptionHandler(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        logger.info("请求发生异常，url:{}, message:{}, 详情:{}", request.getRequestURL().toString(), exception.getMessage(), exception);
        ResultVO resultVO = new ResultVO();
        resultVO.setDomain(domain);
        if (exception instanceof NoHandlerFoundException) {
            resultVO.setCode(ExceptionEnum.NO_HANDLER_FOUND_ERROR.getCode());
            resultVO.setMsg(ExceptionEnum.NO_HANDLER_FOUND_ERROR.getMessage());
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            resultVO.setCode(ExceptionEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_ERROR.getCode());
            resultVO.setMsg(ExceptionEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_ERROR.getMessage());
        } else if (exception instanceof HttpMediaTypeNotSupportedException) {
            resultVO.setCode(ExceptionEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED_ERROR.getCode());
            resultVO.setMsg(ExceptionEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED_ERROR.getMessage());
        } else {
            resultVO.setCode(ExceptionEnum.UNEXPECTED_ERROR.getCode());
            resultVO.setMsg(ExceptionEnum.UNEXPECTED_ERROR.getMessage());
        }
        return resultVO;
    }

    /**
     * 针对全局异常的处理
     *
     * @param exception 全局异常
     * @param request   http request
     * @param response  http response
     * @return 异常处理结果
     */
    @ExceptionHandler(value = Throwable.class)
    public ResultVO throwableHandler(Exception exception, HttpServletRequest request, HttpServletResponse response) {

        logger.info("请求发生异常，url:{}, message:{}, 详情:{}", request.getRequestURL().toString(), exception.getMessage(), exception);
        ResultVO resultVO = new ResultVO();
        resultVO.setDomain(domain);
        resultVO.setCode(ExceptionEnum.UNEXPECTED_ERROR.getCode());
        resultVO.setMsg(ExceptionEnum.UNEXPECTED_ERROR.getMessage());
        return resultVO;
    }
}
