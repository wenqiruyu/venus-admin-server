package com.server.venus.service.impl;

import com.server.venus.mapper.IVenusUserFriendMapper;
import com.server.venus.service.IVenusUserFriendService;
import com.server.venus.vo.VenusUserFriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusUserFriendServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/11
 * 修改人：yingx
 * 修改时间： 2019/12/11
 * 修改备注：
 */
@Service
public class VenusUserFriendServiceImpl implements IVenusUserFriendService {

    @Autowired
    private IVenusUserFriendMapper venusUserFriendMapper;

    @Override
    public List<VenusUserFriendVO> getUserFriend(String userId) {


        return null;
    }
}
