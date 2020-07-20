package com.esrichina.geoservices.constant;

/**
 * 数据库与框架数据类型映射常量
 */
public enum DataTypeConstant {
    /* DATE */
    DATE("date", "import java.util.Date;\r\n", "Date"),
    /* TIME */
    TIME("time", "import java.util.Date;\r\n", "Date"),
    /* TIMESTAMPWITHOUT */
    TIMESTAMPWITHOUT("timestamp(0) without time zone", "import java.util.Date;\r\n", "Date"),
    /* TIMESTAMPWITH */
    TIMESTAMPWITH("timestamp with time zone", "import java.util.Date;\r\n", "Date"),
    /* VARCHAR */
    VARCHAR("varchar", "", "String"),
    /* VARCHAR */
    BAK("-", "", ""),
    /* TEXT */
    TEXT("text", "", "String"),
    /* INT2 */
    INT2("int2", "", "Integer"),
    /* SMALLINT */
    SMALLINT("smallint", "", "Integer"),
    /* INT4 */
    INT4("INT4", "", "Integer"),
    /* INT8 */
    INT8("INT8", "", "Long"),
    /* FLOAT4 */
    FLOAT4("float4", "", "Float"),
    /* FLOAT8 */
    FLOAT8("float8", "", "Double"),
    /* NUMERIC */
    NUMERIC("numeric", "java.math.BigDecimal;\r\n", "BigDecimal"),
    /* BOOL */
    BOOL("boolean", "", "Boolean");

    private String postgre;
    private String rely;
    private String type;

    public String getPostgre() {
        return postgre;
    }

    public void setPostgre(String postgre) {
        this.postgre = postgre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRely() {
        return rely;
    }

    public void setRely(String rely) {
        this.rely = rely;
    }

    public static DataTypeConstant getPackge(String postgre) {
        for (DataTypeConstant dataTypeConstant : values()) {
            if (dataTypeConstant.getPostgre().equals(postgre)) {
                return dataTypeConstant;
            }
        }
        return null;
    }

    private DataTypeConstant(String postgre, String rely, String type) {
        this.postgre = postgre;
        this.rely = rely;
        this.type = type;
    }

    private DataTypeConstant() {
    }

}
