package com.server.venus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.server.venus.mapper.IVenusUserMapper;
import com.server.venus.service.IVenusUserService;
import com.server.venus.vo.RegisterUserVO;
import com.server.venus.vo.VenusUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：venus
 * 类名称：VenusUserServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
@Service
public class VenusUserServiceImpl implements IVenusUserService {

    @Autowired
    private IVenusUserMapper venusUserMapper;

    @Override
    public void addVenusUser(RegisterUserVO registerUserVO) {

        venusUserMapper.addVenusUser(registerUserVO);
    }

    @Override
    public IPage<VenusUserVO> getAllUser(VenusUserVO venusUser, int page, int pageSize) {

        Page<VenusUserVO> venusUserPage = new Page<>(page, pageSize);
        venusUserPage.setRecords(venusUserMapper.getPageUser(venusUserPage, venusUser));
        return venusUserPage;
    }

    @Override
    public VenusUserVO getUserByName(String username) {

        VenusUserVO userByName = venusUserMapper.getUserByName(username);
        return userByName;
    }

    @Override
    public void delUserAccount(String username) {

        venusUserMapper.delUserAccount(username);
    }
}
