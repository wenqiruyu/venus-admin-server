package com.server.venus.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 项目名称：venus
 * 类名称：LoginUserVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
public class LoginUserVO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
