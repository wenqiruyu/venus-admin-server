package com.server.venus.controller;

import com.server.venus.annotation.LogAnnotation;
import com.server.venus.enums.ExceptionEnum;
import com.server.venus.exception.ExtenException;
import com.server.venus.service.IVenusRoleService;
import com.server.venus.vo.ResultVO;
import com.server.venus.vo.VenusRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusRoleController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/2
 * 修改人：yingx
 * 修改时间： 2019/12/2
 * 修改备注：
 */
@RestController
@RequestMapping("/role")
@Api(tags = "【2】启明星权限模块", position = 1)
public class VenusRoleController {

    private static final Logger logger = LoggerFactory.getLogger(VenusUserController.class);

    @Autowired
    private IVenusRoleService venusRoleService;

    @LogAnnotation(value = "新增权限配置")
    @PostMapping("/addRole")
    @ApiOperation(value = "新增权限配置", notes = "添加用户的权限配置", position = 1)
    public ResultVO addUserRole(@RequestBody VenusRoleVO venusRoleVO) {

        logger.info("VenusRoleController addRole start ... roleName:{}, nameZh:{}", venusRoleVO.getRoleName(), venusRoleVO.getNameZh());
        if (StringUtils.isBlank(venusRoleVO.getRoleName()) || StringUtils.isBlank(venusRoleVO.getNameZh())) {
            throw new ExtenException("addRole", ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getCode(),
                    ExceptionEnum.PARAM_VALIDATED_UN_PASS_NULL.getMessage());
        }
        venusRoleService.addRole(venusRoleVO);
        return null;
    }

}
