package com.server.venus.service.impl;

import com.server.venus.mapper.IVenusRoleMapper;
import com.server.venus.service.IVenusRoleService;
import com.server.venus.vo.VenusRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusRoleServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/2
 * 修改人：yingx
 * 修改时间： 2019/12/2
 * 修改备注：
 */
@Service
public class VenusRoleServiceImpl implements IVenusRoleService {

    @Autowired
    private IVenusRoleMapper venusRoleMapper;

    @Override
    public void addRole(VenusRoleVO venusRoleVO) {

        venusRoleMapper.addRole(venusRoleVO);
    }
}
