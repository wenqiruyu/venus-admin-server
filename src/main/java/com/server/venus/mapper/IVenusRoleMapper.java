package com.server.venus.mapper;

import com.server.venus.vo.VenusRoleVO;

/**
 * 项目名称：venus-admin-server
 * 类名称：IVenusRoleMapper
 * 类描述：用户权限配置
 * 创建人：yingx
 * 创建时间： 2019/12/2
 * 修改人：yingx
 * 修改时间： 2019/12/2
 * 修改备注：
 */
public interface IVenusRoleMapper {

    void addRole(VenusRoleVO venusRoleVO);
}
