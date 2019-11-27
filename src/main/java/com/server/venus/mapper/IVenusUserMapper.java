package com.server.venus.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.server.venus.vo.RegisterUserVO;
import com.server.venus.vo.VenusUserVO;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：IVenusUserMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
public interface IVenusUserMapper {

    void addVenusUser(RegisterUserVO registerUserVO);

    List<VenusUserVO> getPageUser(Page<VenusUserVO> venusUserPage, VenusUserVO venusUser);

    VenusUserVO getUserByName(String username);

    void delUserAccount(String username);
}
