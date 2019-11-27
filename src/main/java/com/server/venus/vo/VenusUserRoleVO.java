package com.server.venus.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 项目名称：venus
 * 类名称：VenusUserRoleVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/25
 * 修改人：yingx
 * 修改时间： 2019/10/25
 * 修改备注：
 */
public class VenusUserRoleVO {

    /**
     * 用户权限名
     */
    @ApiModelProperty(value = "用户权限名", required = true)
    private String roleName;

    /**
     * 权限中文名
     */
    @ApiModelProperty(value = "权限中文名", required = true)
    private String nameZh;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    @Override
    public String toString() {
        return "VenusUserRoleVO{" +
                "roleName='" + roleName + '\'' +
                ", nameZh='" + nameZh + '\'' +
                '}';
    }
}
