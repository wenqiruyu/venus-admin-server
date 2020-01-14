package com.server.venus.controller;

import com.server.venus.entity.UserReadHistory;
import com.server.venus.service.IUserReadHistoryService;
import com.server.venus.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：UserReadHistoryController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2020/1/14
 * 修改人：yingx
 * 修改时间： 2020/1/14
 * 修改备注：
 */
@RestController
@RequestMapping("/history")
public class UserReadHistoryController {

    private static final Logger logger = LoggerFactory.getLogger(UserReadHistoryController.class);

    @Autowired
    private IUserReadHistoryService userReadHistoryService;

    @PostMapping(value = "/create")
    public ResultVO create(@RequestBody UserReadHistory userReadHistory) {

        userReadHistoryService.addUserReadHistory(userReadHistory);
        return new ResultVO();
    }

    @PostMapping(value = "/delete")
    public ResultVO delete(@RequestParam("userReadHistoryList") List<UserReadHistory> userReadHistoryList) {

        userReadHistoryService.deleteUserReadHistory(userReadHistoryList);
        return new ResultVO();
    }

    @GetMapping(value = "/list")
    public ResultVO list(Long userId) {

        List<UserReadHistory> userReadHistory = userReadHistoryService.getUserReadHistory(userId);
        return new ResultVO(userReadHistory);
    }
}
