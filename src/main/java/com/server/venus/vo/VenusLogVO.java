package com.server.venus.vo;

import java.util.Date;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusLogVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
public class VenusLogVO {

    /**
     * 日志名称
     */
    private String logName;

    /**
     * 用户id
     */
    private String userName;

    /**
     * 用户的ip地址
     */
    private String userIp;

    /**
     * api名称
     */
    private String api;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求类型 get post update delete
     */
    private String requestType;

    /**
     * 返回的具体消息
     */
    private String message;

    /**
     * 若出现异常记录异常信息
     */
    private String exceptionMsg;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VenusLogVO{" +
                "logName='" + logName + '\'' +
                ", userName='" + userName + '\'' +
                ", userIp='" + userIp + '\'' +
                ", api='" + api + '\'' +
                ", methodName='" + methodName + '\'' +
                ", requestType='" + requestType + '\'' +
                ", message='" + message + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
