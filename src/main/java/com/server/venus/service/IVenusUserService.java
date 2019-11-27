package com.server.venus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.venus.vo.RegisterUserVO;
import com.server.venus.vo.VenusUserVO;

/**
 * 项目名称：venus
 * 类名称：IVenusUserService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
public interface IVenusUserService {

    void addVenusUser(RegisterUserVO registerUserVO);

    IPage<VenusUserVO> getAllUser(VenusUserVO venusUser, int page, int pageSize);

    VenusUserVO getUserByName(String username);

    void delUserAccount(String username);
}
