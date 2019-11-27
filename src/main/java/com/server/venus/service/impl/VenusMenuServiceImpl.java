package com.server.venus.service.impl;

import com.server.venus.entity.Menu;
import com.server.venus.mapper.IVenusMenuMapper;
import com.server.venus.service.IVenusMenuService;
import com.server.venus.vo.VenusUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：learning-blog
 * 类名称：VenusMenuServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/15
 * 修改人：yingx
 * 修改时间： 2019/10/15
 * 修改备注：
 */
@Service
public class VenusMenuServiceImpl implements IVenusMenuService {

    @Autowired
    private IVenusMenuMapper venusMenuMapper;

    @Override
    public List<Menu> getAllMenu() {

        return venusMenuMapper.getAllMenu();
    }

    @Override
    public List<Menu> getMenuByUser(VenusUserVO venusUserVO) {

        return venusMenuMapper.getMenuByUser(venusUserVO);
    }
}
