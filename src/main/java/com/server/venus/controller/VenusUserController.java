package com.server.venus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.venus.annotation.LogAnnotation;
import com.server.venus.entity.UserRole;
import com.server.venus.enums.ExceptionEnum;
import com.server.venus.enums.ResultEnum;
import com.server.venus.exception.ExtenException;
import com.server.venus.service.IVenusUserRoleService;
import com.server.venus.service.IVenusUserService;
import com.server.venus.utils.IdWorkerUtils;
import com.server.venus.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@Api(tags = "【1】启明星用户模块", position = 1)
public class VenusUserController {

    private static final Logger logger = LoggerFactory.getLogger(VenusUserController.class);

    @Value("${idWorker.workerId}")
    private Integer workerId;

    @Value("${idWorker.datacenterId}")
    private Integer datacenterId;

    @Autowired
    private IVenusUserService venusUserService;

    @Autowired
    private IVenusUserRoleService venusUserRoleService;

    /**
     * 开放用户注册功能
     *
     * @param registerUserVO 注册对象
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/25
     */
    @LogAnnotation(value = "用户注册")
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "开放用户注册功能", position = 1)
    public ResultVO register(@RequestBody RegisterUserVO registerUserVO, HttpServletRequest request) {

        logger.info("VenusUserController register start ... Username:{}, Password:{}",
                registerUserVO.getUsername(), StringUtils.isBlank(registerUserVO.getPassword()) ? null : "******");
        if (StringUtils.isBlank(registerUserVO.getUsername()) || StringUtils.isBlank(registerUserVO.getPassword())) {
            throw new ExtenException("register", ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getCode(),
                    ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getMessage());
        }
        // 用户注册需将用户密码进行加密
        registerUserVO.setPassword(new BCryptPasswordEncoder().encode(registerUserVO.getPassword()));
        // 注册成功，为其分配默认角色
        VenusUserVO userByName = venusUserService.getUserByName(registerUserVO.getUsername());
        if (userByName == null) {
            // 生成userId
            long userId = new IdWorkerUtils(workerId, datacenterId).nextId();
            registerUserVO.setUserId(userId);
            venusUserService.addVenusUser(registerUserVO);
            // 注册成功添加用户权限 注册用户都默认为普通用户
            VenusUserRoleVO userRoleVO = new VenusUserRoleVO();
            userRoleVO.setRoleId(1L);
            userRoleVO.setUserId(userId);
            venusUserRoleService.addUserRole(userRoleVO);
            logger.info("VenusUserController register end ... userId:{}", userId);
            return new ResultVO();
        } else {
            logger.info("VenusUserController register end ... result:{}", "注册失败,用户名已存在");
            List<ErrorDataVO> errorList = new ArrayList<>();
            errorList.add(new ErrorDataVO("name", "用户名已存在"));
            throw new ExtenException("register", ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg(), errorList);
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
    @LogAnnotation(value = "用户登录")
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
    @ApiOperation(value = "获取全部户用信息", notes = "分页查询全部用户信息", position = 3)
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
            throw new ExtenException("getAllUser", ExceptionEnum.UNEXPECTED_ERROR.getCode(), ExceptionEnum.UNEXPECTED_ERROR.getMessage());
        }
        logger.info("VenusUserController getAllUser end ...result:{}", records);
        return new ResultVO(records);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/10/25
     */
    @PostMapping("/getUser/name")
    @ApiOperation(value = "查询用户信息（用户名）", notes = "根据用户名查询用户信息", position = 4)
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ResultVO getUserByName(@RequestParam String username) {

        logger.info("VenusUserController getUserByName start ...username:{}", username);
        if (StringUtils.isBlank(username)) {
            throw new ExtenException("getUser", ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getCode(),
                    ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getMessage());
        }
        VenusUserVO userByName = null;
        try {
            userByName = venusUserService.getUserByName(username);
        } catch (Exception e) {
            logger.error("VenusUserController getUserByName error ...", e);
            throw new ExtenException("getAllUser", ExceptionEnum.UNEXPECTED_ERROR.getCode(), ExceptionEnum.UNEXPECTED_ERROR.getMessage());
        }
        logger.info("VenusUserController getUserByName end ...result:{}", userByName);
        return new ResultVO(userByName);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户注销登录", tags = "用户进行账号注销操作", position = 5)
    public ResultVO logout() {

        return null;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户（用户名）", tags = "根据用户名进行删除用户", position = 6)
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ResultVO delAccount(@RequestParam String username) {

        logger.info("VenusUserController getUserByName delAccount ... Username:{}", username);
        if (StringUtils.isBlank(username)) {
            throw new ExtenException("register", ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getCode(),
                    ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getMessage());
        }
        // 需将用户表相关用户和权限删除
        logger.info("VenusUserController getUserByName end");
        return new ResultVO();
    }
}
