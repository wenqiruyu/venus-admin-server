package com.server.venus.mapper;

import com.server.venus.entity.UserReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：IUserReadHistoryMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2020/1/14
 * 修改人：yingx
 * 修改时间： 2020/1/14
 * 修改备注：
 */
public interface IUserReadHistoryMapper extends MongoRepository<UserReadHistory, String> {

    List<UserReadHistory> getUserReadHistory(Long userId);
}
