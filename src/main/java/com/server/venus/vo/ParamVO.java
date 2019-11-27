package com.server.venus.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 项目名称：venus-admin-server
 * 类名称：ParamVO
 * 类描述：公共参数
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
public class ParamVO {

    @NotEmpty(message = "token不能为空")
    @ApiModelProperty(value = "token令牌", required = true)
    private String token;

    @NotNull(message = "时间戳不能为空")
    @ApiModelProperty(value = "时间戳", required = true)
    private Integer time;

    @NotEmpty(message = "版本号不能为空")
    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ParamVO{" +
                "token='" + token + '\'' +
                ", time=" + time +
                ", version='" + version + '\'' +
                '}';
    }
}
