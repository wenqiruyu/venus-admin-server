package com.server.venus.service;

import com.server.venus.entity.Menu;
import com.server.venus.vo.VenusUserVO;

import java.util.List;

/**
 * 项目名称：learning-blog
 * 类名称：IVenusMenuService
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/15
 * 修改人：yingx
 * 修改时间： 2019/10/15
 * 修改备注：
 */
public interface IVenusMenuService {

    List<Menu> getAllMenu();

    List<Menu> getMenuByUser(VenusUserVO venusUserVO);
}
