package com.server.venus.controller;

import com.server.venus.entity.Menu;
import com.server.venus.service.IVenusMenuService;
import com.server.venus.vo.ResultVO;
import com.server.venus.vo.VenusUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：VenusMenuController
 * 类描述：菜单控制器类
 * 创建人：yingx
 * 创建时间： 2019/10/28
 * 修改人：yingx
 * 修改时间： 2019/10/28
 * 修改备注：
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "【3】启明星菜单模块", position = 1)
public class VenusMenuController {

    private static final Logger logger = LoggerFactory.getLogger(VenusUserController.class);

    @Autowired
    private IVenusMenuService venusMenuService;

    /**
     * 获取系统的全部菜单列表
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/28
     */
    @PostMapping("/getAllMenu")
    @ApiOperation(value = "获取全部菜单", notes = "获取系统的全部菜单列表", position = 2)
    public ResultVO getAllMenu() {

        logger.info("VenusMenuController getAllMenu start ...");
        List<Menu> allMenu = null;
        try {
            allMenu = venusMenuService.getAllMenu();
        } catch (Exception e) {
            logger.error("VenusMenuController getAllMenu error ...！", e);
//            ResultVO.fail("系统错误！");
        }
        logger.info("VenusMenuController getAllMenu end ...Result:{}", allMenu);
//        return ResultVO.success(allMenu);
        return null;
    }

    /**
     * 根据用户查询用户有权访问的菜单
     *
     * @param venusUserVO
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/29
     */
    @PostMapping("/getMenu/user")
    @ApiOperation(value = "获取用户权限下的菜单", notes = "根据用户查询用户有权访问的菜单", position = 2)
    public ResultVO getMenuByUser(@RequestBody VenusUserVO venusUserVO) {

        logger.info("VenusMenuController getMenuByUser start ...VenusUserVo:{}", venusUserVO);
        List<Menu> menuByUser = null;
        try {
            menuByUser = venusMenuService.getMenuByUser(venusUserVO);
        } catch (Exception e) {
            logger.error("VenusMenuController getMenuByUser error ...", e);
//            ResultVO.fail("系统错误！");
        }
        logger.info("VenusMenuController getMenuByUser end ...Result:{}", menuByUser);
//        return ResultVO.success(menuByUser);
        return null;
    }
}
