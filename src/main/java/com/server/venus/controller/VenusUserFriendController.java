package com.server.venus.controller;

import com.server.venus.annotation.LogAnnotation;
import com.server.venus.service.IVenusUserFriendService;
import com.server.venus.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusUserFriendController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/11
 * 修改人：yingx
 * 修改时间： 2019/12/11
 * 修改备注：
 */
@RestController
@RequestMapping("/ufriend")
@Api(tags = "【3】启明星用户好友模块", position = 1)
public class VenusUserFriendController {

    private static final Logger logger = LoggerFactory.getLogger(VenusUserFriendController.class);

    @Autowired
    private IVenusUserFriendService venusUserFriendService;

    @CrossOrigin
    @LogAnnotation(value = "获取好友列表")
    @PostMapping("/getUserFriend")
    @ApiOperation(value = "好友列表", notes = "获取好友列表", position = 1)
    private ResultVO getUserFriend(@PathVariable("page") int page) {

        return null;
    }
}
