package com.server.venus.exception;

/**
 * 项目名称：venus-admin-server
 * 类名称：ExtenException
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
public class ExtenException extends RuntimeException{

    /**
     * 业务域
     */
    private String domain;

    /**
     * 业务异常码 ( 详情参加文档说明 )
     */
    private Long code;

    /**
     * 业务异常信息
     */
    private String message;

    /**
     * 额外数据，可支持扩展
     */
    private Object data;

    /**
     * cause
     */
    private Throwable cause;

    public ExtenException() {
    }

    public ExtenException(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExtenException(String domain, Long code, String message) {
        this.domain = domain;
        this.code = code;
        this.message = message;
    }

    public ExtenException(String domain, Long code, String message, Object data) {
        this.domain = domain;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getDomain() {
        return domain;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Throwable getCause() {
        return cause;
    }
}
