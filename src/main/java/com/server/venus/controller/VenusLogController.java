package com.server.venus.controller;

import com.server.venus.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：venus
 * 类名称：VenusLogController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/2
 * 修改人：yingx
 * 修改时间： 2019/11/2
 * 修改备注：
 */
@RestController
@RequestMapping("/log")
@Api(tags = "[1]启明星日志模块", position = 1)
public class VenusLogController {

    public ResultVO getLoginLog() {

        return null;
    }
}
