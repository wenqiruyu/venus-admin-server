package com.server.venus.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：RegisterUserVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/25
 * 修改人：yingx
 * 修改时间： 2019/10/25
 * 修改备注：
 */
public class RegisterUserVO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    /**
     * 家庭座机
     */
    @ApiModelProperty(value = "家庭座机", required = true)
    private String telePhone;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址", required = true)
    private String address;

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

    /**
     * 用户权限
     */
    @ApiModelProperty(value = "用户权限", required = true)
    private List<VenusUserRoleVO> roles;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", required = true)
    private String userFace;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public List<VenusUserRoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<VenusUserRoleVO> roles) {
        this.roles = roles;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RegisterUserVO{" +
                "phone='" + phone + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", userFace='" + userFace + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
