package com.server.venus.service.impl;

import com.server.venus.entity.Log;
import com.server.venus.mapper.IVenusLogMapper;
import com.server.venus.service.IVenusLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusLogServiceImpl
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
@Service
public class VenusLogServiceImpl implements IVenusLogService {

    private static final Logger logger = LoggerFactory.getLogger(VenusLogServiceImpl.class);

    @Autowired
    private IVenusLogMapper venusLogMapper;

    @Override
    public List<Log> getAllLog() {
        return null;
    }

    @Override
    public void addVenusLog(Log log) {

        logger.info("VenusLogServiceImpl addVenusLog start ... log:{}", log);
        venusLogMapper.addVenusLog(log);
        logger.info("VenusLogServiceImpl addVenusLog end ...");
    }
}
