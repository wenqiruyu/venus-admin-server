package com.server.venus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.venus.enums.ResultCodeEnum;
import com.server.venus.service.IVenusUserService;
import com.server.venus.vo.LoginUserVO;
import com.server.venus.vo.RegisterUserVO;
import com.server.venus.vo.ResultVO;
import com.server.venus.vo.VenusUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：venus
 * 类名称：VenusUserController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
@RestController
@RequestMapping("/user")
@Api(tags = "[1]启明星用户模块", position = 1)
public class VenusUserController {

    private static final Logger logger = LoggerFactory.getLogger(VenusUserController.class);

    @Autowired
    private IVenusUserService venusUserService;

    /**
     * 开放用户注册功能
     *
     * @param registerUserVO
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/25
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "开放用户注册功能", position = 2)
    public ResultVO register(@RequestBody RegisterUserVO registerUserVO) {

        logger.info("VenusUserController register start ... Username:{}, Password:{}",
                registerUserVO.getUsername(), StringUtils.isBlank(registerUserVO.getPassword()) ? null : "******");
        if (registerUserVO == null || StringUtils.isBlank(registerUserVO.getUsername()) || StringUtils.isBlank(registerUserVO.getPassword())) {
            return ResultVO.fail(ResultCodeEnum.PARAMS_ERROR_NULL);
        }
        // 用户注册需将用户密码进行加密
        registerUserVO.setPassword(new BCryptPasswordEncoder().encode(registerUserVO.getPassword()));
        try {
            venusUserService.addVenusUser(registerUserVO);
            // 注册成功，为其分配默认角色
            VenusUserVO userByName = venusUserService.getUserByName(registerUserVO.getUsername());
            if (userByName == null) {

            }
            return ResultVO.success();
        } catch (Exception e) {
            logger.error("VenusUserController register error!", e);
            return ResultVO.fail("注册失败");
        }
    }

    /**
     * 使用spring security自带的登录功能
     *
     * @param loginUserVO
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/25
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "使用spring security自带的登录功能", position = 2)
    public ResultVO login(@RequestBody LoginUserVO loginUserVO) {

        logger.info("VenusUserController login start ... loginUserVO:{}", loginUserVO);
        return null;
    }


    /**
     * 分页查询全部用户信息
     *
     * @param page
     * @param pageSize
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/24
     */
    @PostMapping("/getAllUser/{page}/{pageSize}")
    @ApiOperation(value = "获取全部户用信息", notes = "分页查询全部用户信息", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "int")
    })
    public ResultVO getAllUser(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {

        logger.info("VenusUserController getAllUser start ...page:{},pageSize:{}", page, pageSize);
        List<VenusUserVO> records = null;
        try {
            IPage<VenusUserVO> allUser = venusUserService.getAllUser(new VenusUserVO(), page, pageSize);
            records = allUser.getRecords();
        } catch (Exception e) {
            logger.error("VenusUserController getAllUser error ...", e);
            return ResultVO.fail("系统错误！");
        }
        logger.info("VenusUserController getAllUser end ...result:{}", records);
        return ResultVO.success(records);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/25
     */
    @PostMapping("getUser/name")
    @ApiOperation(value = "查询用户信息（用户名）", notes = "根据用户名查询用户信息", position = 2)
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ResultVO getUserByName(@RequestParam String username) {

        logger.info("VenusUserController getUserByName start ...username:{}", username);
        VenusUserVO userByName = null;
        try {
            userByName = venusUserService.getUserByName(username);
        } catch (Exception e) {
            logger.error("VenusUserController getUserByName error ...", e);
            return ResultVO.fail("系统错误！");
        }
        logger.info("VenusUserController getUserByName end ...result:{}", userByName);
        return ResultVO.success(userByName);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户注销登录", tags = "用户进行账号注销操作", position = 2)
    public ResultVO logout() {

        return null;
    }

    @DeleteMapping("/delete")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ResultVO delAccount(@RequestParam String username) {

        logger.info("VenusUserController getUserByName delAccount ... Username:{}", username);
        if (StringUtils.isBlank(username)) {
            return ResultVO.fail(ResultCodeEnum.PARAMS_ERROR_NULL);
        }
        // 需将用户表相关用户和权限删除
        logger.info("VenusUserController getUserByName end");
        return ResultVO.success();
    }
}
