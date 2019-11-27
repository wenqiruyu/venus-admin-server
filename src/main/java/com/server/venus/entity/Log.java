package com.server.venus.entity;

import java.util.Date;

/**
 * 项目名称：venus
 * 类名称：Log
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/2
 * 修改人：yingx
 * 修改时间： 2019/11/2
 * 修改备注：
 */
public class Log {

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
     * 是否有效 0有效 1删除 2异常日志
     */
    private Integer isEnable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更改时间
     */
    private Date updateTime;

    /**
     * 最后一次修改人
     */
    private String lastUpdateBy;

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

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logName='" + logName + '\'' +
                ", userName='" + userName + '\'' +
                ", userIp='" + userIp + '\'' +
                ", api='" + api + '\'' +
                ", methodName='" + methodName + '\'' +
                ", requestType='" + requestType + '\'' +
                ", message='" + message + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                ", isEnable=" + isEnable +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                '}';
    }
}
