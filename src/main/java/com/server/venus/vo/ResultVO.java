package com.server.venus.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.server.venus.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：ResultVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
@ApiModel("统一 Response 返回值")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务域或应用标识
     */
    @ApiModelProperty(name = "业务域或应用标识", notes = "只有在产生错误时才会有这个字段")
    private String domain;

    /**
     * 结果码
     */
    @ApiModelProperty(name = "操作结果码", notes = "正确响应时为200，错误为对应的错误码")
    private long code;

    /**
     * 响应信息
     */
    @ApiModelProperty(name = "响应信息", notes = "正确响应为SUCCESS，错误响应对应的错误信息")
    private String msg;

    /**
     * 响应体
     */
    @ApiModelProperty(name = "操作响应体", notes = "正确时该字段才会被赋值")
    private T data;

    @ApiModelProperty("错误信息")
    private List<ErrorDataVO> errors;

    public ResultVO() {
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getMsg());
    }

    public ResultVO(T data) {

        this.setData(data);
        this.setCode(ResultEnum.SUCCESS.getCode());
        this.setMsg(ResultEnum.SUCCESS.getMsg());
    }

    public ResultVO(String domain, Long code, String message) {

        this.setDomain(domain);
        this.setCode(code);
        this.setMsg(message);
    }

    public ResultVO(String domain, Long code, String message, List<ErrorDataVO> errors) {

        this.setDomain(domain);
        this.setCode(code);
        this.setMsg(message);
        this.setErrors(errors);
    }

    public void addError(String name, String message) {

        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        ErrorDataVO errorData = new ErrorDataVO();
        errorData.setName(name);
        errorData.setMsg(message);
        this.errors.add(errorData);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ErrorDataVO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDataVO> errors) {
        this.errors = errors;
    }
}
