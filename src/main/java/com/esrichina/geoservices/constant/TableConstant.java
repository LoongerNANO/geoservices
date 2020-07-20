package com.esrichina.geoservices.constant;

/**
 * 数据库表设计属性常量
 */
public enum TableConstant {

    /* 表所在的类别 */
    TABLE_CAT(1, "TABLE_CAT"),
    /* 表所在的模式 */
    TABLE_SCHEM(2, "TABLE_SCHEM"),
    /* 表的名称 */
    TABLE_NAME(3, "TABLE_NAME"),
    /* 表的类型 */
    TABLE_TYPE(4, "TABLE_TYPE"),
    /* 解释性的备注 */
    REMARKS(5, "REMARKS"),
    /* 编目类型 */
    TYPE_CAT(6, "TYPE_CAT"),
    /* 模式类型 */
    TYPE_SCHEM(7, "TYPE_SCHEM"),
    /* 类型名称 */
    TYPE_NAME(8, "TYPE_NAME"),
    /* SELF_REFERENCING_COL_NAME */
    SELF_REFERENCING_COL_NAME(9, "SELF_REFERENCING_COL_NAME"),
    /* REF_GENERATION */
    REF_GENERATION(10, "REF_GENERATION");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private TableConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private TableConstant() {
    }

}
