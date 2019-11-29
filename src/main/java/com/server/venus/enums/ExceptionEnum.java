package com.server.venus.enums;

/**
 * 项目名称：venus-admin-server
 * 类名称：ExceptionEnum
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/11/29
 * 修改人：yingx
 * 修改时间： 2019/11/29
 * 修改备注：
 */
public enum ExceptionEnum {

    UNEXPECTED_ERROR(403001L, "服务发生非预期异常，请联系管理员！"),
    PARAM_VALIDATED_UN_PASS(403002L, "参数校验不通过，请检查参数或联系管理员！"),
    PARAM_VALIDATED_UN_PASS_NULL(403003L, "参数校验不通过，参数必填字段存在空值，请检查参数或联系管理员！"),
    NO_HANDLER_FOUND_ERROR(403004L, "未找到对应的处理器，请检查 API 或联系管理员！"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_ERROR(403005L, "不支持的请求方法，请检查 API 或联系管理员！"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_ERROR(403006L, "不支持的互联网媒体类型，请检查 API 或联系管理员");

    private Long code;

    private String message;

    ExceptionEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
