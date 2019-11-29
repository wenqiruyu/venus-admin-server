package com.server.venus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目名称：venus-admin-server
 * 类名称：ErrorDataVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
@ApiModel("统一 Response 返回值的错误信息")
public class ErrorDataVO {

    @ApiModelProperty(name = "错误项", notes = "错误的具体项")
    private String name;

    @ApiModelProperty(name = "错误项说明", notes = "错误的具体项说明")
    private String msg;

    public ErrorDataVO() {
    }

    public ErrorDataVO(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
