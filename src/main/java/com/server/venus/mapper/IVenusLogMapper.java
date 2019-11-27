package com.server.venus.mapper;

import com.server.venus.entity.Log;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：IVenusLogMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
public interface IVenusLogMapper {

    List<Log> getAllLog();

    void addVenusLog(Log log);

    void updateVenusLog(Log log);
}
