package com.esrichina.geoservices.constant;

/**
 * 系统返回常量
 */
public enum WebResultStatusConstant {

    // ====== 请求结果
    // 成功
    SUCCESS_OPERATION(0, "操作成功"),

    // 授权相关


    // 用户相关

    // 业务相关
    PROCESS_CREATE(3001, "数据创建失败"),
    PROCESS_EDIT(3002, "数据编辑失败"),
    PROCESS_DELETE(3003, "数据删除失败"),
    PROCESS_WARN(3004, "数据不可操作"),
    PROCESS_REPRT(3005, "数据重复"),
    PROCESS_NULL(3006, "没有此数据"),
    NODE_LEAF(3011, "叶子节点无加载数据"),

    // 参数相关
    ARGUMENT_ERROR(4001, "参数为空或格式不正确"),

    // 系统异常
    SERVER_ERROR(5001, "系统异常");


//    SUCCESS(200, "success"),
//    FAIL(400, "fail"),
//    VISIT_NOT_AUTHORIZED(401, "未授权"),
//    ARGUMENT_ERROR(402,"参数错误"),
//    ARGUMENT_EXCEPTION(407, "参数存在异常"),
//    ARGUMENT_TOKEN_EMPTY(409, "Token为空"),
//    ARGUMENT_TOKEN_INVALID(410, "Token无效"),
//    SERVER_ERROR(501, "服务端异常"),
//    SERVER_SQL_ERROR(503,"数据库操作出现异常"),
//    SERVER_DATA_REPEAT(504, "服务器数据已存在"),
//    SERVER_DATA_NOTEXIST(505,"数据不存在"),
//    SERVER_DATA_STATUS_ERROR(506, "数据状态错误"),
//    SERVER_SMS_SEND_FAIL(701, "短信发送失败"),


    private Integer code;
    private String msg;

    private WebResultStatusConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private WebResultStatusConstant() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
