package com.server.venus.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.server.venus.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 项目名称：venus
 * 类名称：ResultVO
 * 类描述：返回格式类
 * 创建人：yingx
 * 创建时间： 2019/9/26
 * 修改人：yingx
 * 修改时间： 2019/9/26
 * 修改备注：
 * <p>
 * 状态码
 * <p>
 * 提示信息
 * <p>
 * 返回数据
 * <p>
 * 只返回错误码
 *
 * @param status
 * @return
 * @author yingx
 * @date 2019/9/26
 * <p>
 * 返回错误码和数据
 * @param status
 * @param data
 * @return
 * @author yingx
 * @date 2019/9/26
 * <p>
 * 返回错误码，错误提示和数据
 * @param status
 * @param msg
 * @param data
 * @return
 * @author yingx
 * @date 2019/9/26
 * <p>
 * 返回错误码、错误提示
 * @param status
 * @param msg
 * @return
 * @author yingx
 * @date 2019/9/26
 */
/*@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 保证json序列化的时候，如果是null对象，key也会消失
public class ResultVO<T> implements Serializable {

    *//**
 * 状态码
 *//*
    private String status;

    *//**
 * 提示信息
 *//*
    private String msg;

    *//**
 * 返回数据
 *//*
    private T data;

    *//**
 * 只返回错误码
 *
 * @param status
 * @return
 * @author yingx
 * @date 2019/9/26
 *//*
    private ResultVO(String status) {
        this.status = status;
    }

    *//**
 * 返回错误码和数据
 *
 * @param status
 * @param data
 * @return
 * @author yingx
 * @date 2019/9/26
 *//*
    private ResultVO(String status, T data) {
        this.status = status;
        this.data = data;
    }

    *//**
 * 返回错误码，错误提示和数据
 *
 * @param status
 * @param msg
 * @param data
 * @return
 * @author yingx
 * @date 2019/9/26
 *//*
    private ResultVO(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    *//**
 * 返回错误码、错误提示
 *
 * @param status
 * @param msg
 * @return
 * @author yingx
 * @date 2019/9/26
 *//*
    private ResultVO(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    */

/**
 * 判断是否返回成功的提示码
 *
 * @param
 * @return boolean
 * @author yingx
 * @date 2019/9/26
 *//*
    @JsonIgnore // 使之不再进行序列化
    public boolean isSuccess() {
        // 响应成功
        return this.status == ResultEnum.SUCCESS.getCode();
    }

    public static <T> ResultVO<T> createBySuccess() {
        return new ResultVO<T>(ResultEnum.SUCCESS.getCode());
    }

    public static <T> ResultVO<T> createBySuccess(T data) {
        return new ResultVO<T>(ResultEnum.SUCCESS.getCode(), "success", data);
    }

    public static <T> ResultVO<T> createBySuccess(String msg, T data) {
        return new ResultVO<T>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultVO<T> createBySuccessMessage(String msg) {
        return new ResultVO<T>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultVO<T> createByError() {
        return new ResultVO<T>(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg());
    }

    public static <T> ResultVO<T> createByErrorCodeMessage(String errorCode, String errorMessage) {
        return new ResultVO<T>(errorCode, errorMessage);
    }

    public static <T> ResultVO<T> createByErrorMessage(String errorMessage) {
        return new ResultVO<T>(ResultEnum.FAIL.getCode(), errorMessage);
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}*/
@ApiModel(value = "ResultVO", description = "公用返回定义")
@JacksonXmlRootElement(localName = "root") // 方便对象和xml的转换
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    @JacksonXmlProperty(localName = "code")
    private String code;

    @ApiModelProperty(value = "返回描述", required = true)
    @JacksonXmlProperty(localName = "message")
    private String message;

    @ApiModelProperty(value = "业务数据", required = false)
    @JacksonXmlProperty(localName = "data")
    private T data;

    public ResultVO() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMsg();
    }

    public ResultVO(T data) {
        this.data = data;
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMsg();
    }

    public ResultVO(ResultCodeEnum codeEnum, T data) {
        this.data = data;
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    public ResultVO(ResultCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @Title：success
     * @Description： 默认成功
     * @return com.cignacmb.web.permission.vo.ResultVO<T>
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> ResultVO<T> success() {
        return new ResultVO("");
    }

    /**
     * @Title：success
     * @Description：自定义成功提醒
     * @param data
     * @return com.cignacmb.web.permission.vo.ResultVO<T>
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>(data);
    }

    /**
     * @Title：success
     * @Description：自定义成功类 codeEnum
     * @param codeEnum
     * @return com.cignacmb.web.permission.vo.ResultVO<T>
     */
    public static <T> ResultVO<T> success(ResultCodeEnum codeEnum) {
        return new ResultVO<T>(codeEnum);
    }

    /**
     * @Title：success
     * @Description：自定义成功类及提醒信息
     * @param codeEnum
     * @param data
     * @return com.cignacmb.web.permission.vo.ResultVO<T>
     */
    public static <T> ResultVO<T> success(ResultCodeEnum codeEnum, T data) {
        return new ResultVO<T>(codeEnum, data);
    }

    /**
     *
     * @Title: fail
     * @Description: 默认返回失败
     * @return
     * @return ResultVO
     * @throws
     */
    public static <T> ResultVO<T> fail() {
        return new ResultVO<T>(ResultCodeEnum.FAIL);
    }

    /**
     *
     * @Title: fail
     * @Description: 返回自定义失败信息
     * @param codeEnum
     * @return
     * @return ResultVO
     * @throws
     */
    public static <T> ResultVO<T> fail(ResultCodeEnum codeEnum) {
        return new ResultVO<T>(codeEnum);
    }

    /**
     *
     * @Title: fail
     * @Description: 返回自定义结果
     * @param data
     * @return
     * @return ResultVO
     * @throws
     */
    public static <T> ResultVO<T> fail(T data) {
        return new ResultVO<T>(ResultCodeEnum.FAIL, data);
    }

    /**
     *
     * @Title: fail
     * @Description: 返回自定义失败信息和结果
     * @return
     * @return ResultVO
     * @throws
     */
    public static <T> ResultVO<T> fail(ResultCodeEnum codeEnum, T data) {
        return new ResultVO<T>(codeEnum, data);
    }

    @Override
    public String toString() {
        return "ResultVO{" + "code='" + code + '\'' + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}