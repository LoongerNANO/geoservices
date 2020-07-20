package com.esrichina.geoservices.model.example;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /*日志唯一标识 */
    private String id;
    /*请求人账号 */
    private String username;
    /*请求人姓名 */
    private String fullname;
    /*请求地址 */
    private String url;
    /*请求方式 */
    private String method;
    /*客户端IP */
    private String ip;
    /*响应类函数 */
    private String functions;
    /*请求参数 */
    private String parameter;
    /*操作内容描述 */
    private String operation;
    /*响应时间 */
    private String time;
    /*请求时间 */
    private String ask;
    /*日志类型 */
    private String log_type;
    /*日志类型名称 */
    private String log_type_name;
    /*错误描述 */
    private String error;
    /*客户端浏览器名称 */
    private String browser;
    /*客户端浏览器版本 */
    private String version;
    /*客户端操作系统 */
    private String os;

}