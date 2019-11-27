package com.server.venus.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：CustomMetaObjectHandler
 * 类描述：保存或更新数据时自动添加修改时间、人，创建时间、人
 * 创建人：yingx
 * 创建时间： 2019/10/21
 * 修改人：yingx
 * 修改时间： 2019/10/21
 * 修改备注：
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        // 添加时自动填充公共对象的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            User user = (User) authentication.getPrincipal();
            setInsertFieldValByName("lastUpdateBy", "admin", metaObject);
            setInsertFieldValByName("createBy", "admin", metaObject);
        }
        setInsertFieldValByName("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        // 修改时自动填充公共对象的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            User user = (User) authentication.getPrincipal();
            setUpdateFieldValByName("lastUpdateBy", "admin", metaObject);
        }
    }
}