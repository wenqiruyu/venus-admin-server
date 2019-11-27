package com.server.venus.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：VenusMenuVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/29
 * 修改人：yingx
 * 修改时间： 2019/10/29
 * 修改备注：
 */
public class VenusMenuVO {

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接", required = true)
    private String url;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径", required = true)
    private String path;

    /**
     * 成分
     */
    @ApiModelProperty(value = "成分", required = true)
    private String component;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    /**
     * 菜单icon
     */
    @ApiModelProperty(value = "菜单icon", required = true)
    private String iconCls;

    private Integer keepAlive;

    private Integer requireAuth;

    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id", required = true)
    private Long parentId;

    /**
     * 菜单允许权限
     */
    @ApiModelProperty(value = "菜单允许权限", required = true)
    private List<VenusUserRoleVO> roles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Integer getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Integer requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<VenusUserRoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<VenusUserRoleVO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "VenusMenuVO{" +
                "url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", keepAlive=" + keepAlive +
                ", requireAuth=" + requireAuth +
                ", parentId=" + parentId +
                ", roles=" + roles +
                '}';
    }
}
