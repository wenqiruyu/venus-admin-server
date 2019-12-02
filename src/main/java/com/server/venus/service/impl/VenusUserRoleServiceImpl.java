package com.server.venus.service.impl;

import com.server.venus.mapper.IVenusUserRoleMapper;
import com.server.venus.service.IVenusUserRoleService;
import com.server.venus.vo.VenusRoleVO;
import com.server.venus.vo.VenusUserRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusUserRoleServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/2
 * 修改人：yingx
 * 修改时间： 2019/12/2
 * 修改备注：
 */
@Service
public class VenusUserRoleServiceImpl implements IVenusUserRoleService {

    @Autowired
    private IVenusUserRoleMapper venusUserRoleMapper;


    @Override
    public void addUserRole(VenusUserRoleVO venusUserRoleVO) {

        venusUserRoleMapper.addUserRole(venusUserRoleVO);
    }
}
