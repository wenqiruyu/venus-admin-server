package com.server.venus.entity;

/**
 * 项目名称：venus-admin-server
 * 类名称：UserRole
 * 类描述：用户和权限关联关系
 * 创建人：yingx
 * 创建时间： 2019/12/2
 * 修改人：yingx
 * 修改时间： 2019/12/2
 * 修改备注：
 */
public class UserRole {

    /**
     * 权限编号
     */
    private Long roleId;

    /**
     * 用户Id
     */
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
