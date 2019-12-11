package com.server.venus.mapper;

import com.server.venus.vo.VenusUserFriendVO;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：IVenusUserFriendMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/11
 * 修改人：yingx
 * 修改时间： 2019/12/11
 * 修改备注：
 */
public interface IVenusUserFriendMapper {

    List<VenusUserFriendVO> getUserFriend(String userId);


}
