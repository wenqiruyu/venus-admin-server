package com.server.venus.mapper;

import com.server.venus.entity.UserDetailsImpl;

/**
 * 项目名称：venus
 * 类名称：IUserDetailsMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
public interface IUserDetailsMapper {

    UserDetailsImpl getUserByName(String username);
}
