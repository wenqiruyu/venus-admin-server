package com.server.venus.service;

import com.server.venus.entity.Log;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：IVenusLogService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/2
 * 修改人：yingx
 * 修改时间： 2019/11/2
 * 修改备注：
 */
public interface IVenusLogService {

    List<Log> getAllLog();

    void addVenusLog(Log log);
}
