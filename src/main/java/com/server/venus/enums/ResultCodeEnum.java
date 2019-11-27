package com.server.venus.enums;

/**
 * 项目名称：venus
 * 类名称：ResultCodeEnum
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/29
 * 修改人：yingx
 * 修改时间： 2019/10/29
 * 修改备注：
 */
public enum ResultCodeEnum {

    //系统级错误代码定义，长度统一为3位,包括常见框架请求响应编码.
    SUCCESS("200", "成功"),
    FAIL("400", "失败"),
    INVALID_AUTHCODE("401", "未授权"),
    SYS_ERROR("402", "系统错误"),
    PARAMS_ERROR("403", "参数错误 "),
    PARAMS_ERROR_FORMAT("403-001", "参数传递方式不正确, 请使用application/json传递参数"),
    PARAMS_ERROR_NULL("403-002", "参数为空"),
    //业务级错误代码定义 ，1开头代表用户账号错误,长度统一为4位
    UNKNOWN_USER("1001", "账号不存在或密码错误"),
    NAME_OR_PASS_EMPTY("1002", "账号或密码不能为空"),
    USER_NOT_EXISTED("1003", "用戶不存在"),
    NO_USE("1004", "账号已冻结,请联系管理员"),
    USER_ACCOUNT_IS_EXP("1005", "账号已过期"),
    USER_ACCOUNT_IS_ENABLED("1006", "账号已被禁用"),
    USER_LOGOUT_SUCCESS("1007", "用户注销成功"),
    ACCOUNT_HAS_NOT_EXISTED("1008", "账号添加失败，请确认输入！"),
    TYPE_NO_VALID("1009", "账户类型无效"),
    USER_ACCOUNT_IS_LOCK("1010", "账号已被锁定"),

    //业务级错误代码定义,2开头代表Token相关校验错误.
    TOKEN_FAIL("2001", "token无效"),
    TOKEN_SUCCESS("2000", "token合法"),
    TOKEN_IS_EXPIRES("2002", "token已过期,请重新申请");

    private String code;
    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
