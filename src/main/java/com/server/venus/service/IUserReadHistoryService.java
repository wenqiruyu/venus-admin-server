package com.server.venus.service;

import com.server.venus.entity.UserReadHistory;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：IUserReadHistoryService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2020/1/14
 * 修改人：yingx
 * 修改时间： 2020/1/14
 * 修改备注：
 */
public interface IUserReadHistoryService {

    void addUserReadHistory(UserReadHistory userReadHistory);

    void deleteUserReadHistory(List<UserReadHistory> userReadHistoryList);

    List<UserReadHistory> getUserReadHistory(Long userId);
}
