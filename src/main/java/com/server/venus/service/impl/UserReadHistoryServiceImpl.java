package com.server.venus.service.impl;

import com.server.venus.entity.UserReadHistory;
import com.server.venus.mapper.IUserReadHistoryMapper;
import com.server.venus.service.IUserReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：UserReadHistoryServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2020/1/14
 * 修改人：yingx
 * 修改时间： 2020/1/14
 * 修改备注：
 */
@Service
public class UserReadHistoryServiceImpl implements IUserReadHistoryService {

    @Autowired
    private IUserReadHistoryMapper userReadHistoryMapper;

    @Override
    public void addUserReadHistory(UserReadHistory userReadHistory) {

        UserReadHistory saveUserReadHistory = userReadHistoryMapper.save(userReadHistory);
        if (saveUserReadHistory == null) {

        }
    }

    @Override
    public void deleteUserReadHistory(List<UserReadHistory> userReadHistoryList) {

        userReadHistoryMapper.deleteAll(userReadHistoryList);
    }

    @Override
    public List<UserReadHistory> getUserReadHistory(Long userId) {

        List<UserReadHistory> userReadHistory = userReadHistoryMapper.getUserReadHistory(userId);
        return userReadHistory;
    }
}
