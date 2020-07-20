package com.esrichina.geoservices.generator;

import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.constant.DataTypeConstant;
import com.esrichina.geoservices.constant.TableConstant;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

@Log4j2
public class CodeGenerator {

    public static void main(String[] args) {
        CodeGenerator pgGen = new CodeGenerator();
        pgGen.generate();


    }

    // 数据库配置信息
    private static final String JDBC_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PWD = "arcgis";
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    // 事件格式化
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // ENTITY包名
    private static String packageEntityPath = "com.esrichina.services.entity";
    // MAPPER包名
    private static String packageMapperPath = "com.esrichina.services.mapper";
    // ISERVICE
    private static String packageIServicePath = "com.esrichina.services.service";
    // SERVICEIMPL
    private static String packageServiceImplPath = "com.esrichina.services.service.Impl";
    // 作者名字
    private static final String authorName = "LOONGER CHEN";
    // 指定表
    private String[] generateTables = null;
    // 指定实体生成所在包的路径
    private static String basePath = new File("").getAbsolutePath();

    private CodeGenerator() {
    }

    /**
     * 表属性信息
     */
    private static String tableAttributeSQL(String tableName) {
        if (tableName == null || "".equals(tableName)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append(" COL_DESCRIPTION(A.ATTRELID, A.ATTNUM) AS COMMENT, ");
        sb.append(" FORMAT_TYPE(A.ATTTYPID, A.ATTTYPMOD) AS TYPE, ");
        sb.append(" A.ATTNAME AS NAME, ");
        sb.append(" A.ATTNOTNULL AS NOTNULL, ");
        sb.append(" A.ATTTYPMOD-4 AS FIELDS_LENGTH ");
        sb.append(" FROM PG_CLASS AS C, PG_ATTRIBUTE AS A ");
        sb.append(" WHERE C.RELNAME =  '" + tableName + "' ");
        sb.append(" AND A.ATTRELID = C.OID AND A.ATTNUM > 0 ");
        String sql = sb.toString();
        log.info(" MESSAGE : SQL - {} ", sql);
        return sql;
    }

    /**
     * 表主键信息
     */
    private static String tableAttributePKSQL(String tableName) {
        if (tableName == null || "".equals(tableName)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT ");
        sb.append(" PG_CONSTRAINT.CONNAME AS PK_NAME, ");
        sb.append(" PG_ATTRIBUTE.ATTNAME AS COLNAME, ");
        sb.append(" PG_TYPE.TYPNAME AS TYPENAME ");
        sb.append(" FROM PG_CONSTRAINT  INNER JOIN PG_CLASS ON PG_CONSTRAINT.CONRELID = PG_CLASS.OID INNER JOIN PG_ATTRIBUTE ON PG_ATTRIBUTE.ATTRELID = PG_CLASS.OID ");
        sb.append(" AND  PG_ATTRIBUTE.ATTNUM = PG_CONSTRAINT.CONKEY[1] INNER JOIN PG_TYPE ON PG_TYPE.OID = PG_ATTRIBUTE.ATTTYPID ");
        sb.append(" WHERE PG_CLASS.RELNAME = '" + tableName + "'");
        sb.append(" AND PG_CONSTRAINT.CONTYPE='P' ");
        String sql = sb.toString();
        log.info(" MESSAGE : SQL - {} ", sql);
        return sql;
    }


    /**
     * 类名称
     */
    private static String category(String tablename, boolean fistCharToUpperCase, String suffix) {
        String separator = "_";
        String under = "";
        tablename = tablename.toLowerCase().replace(separator, " ");
        String sarr[] = tablename.split(" ");
        for (int i = 0; i < sarr.length; i++) {
            String w = sarr[i].substring(0, 1).toUpperCase() + sarr[i].substring(1);
            under += w;
        }
        if (!fistCharToUpperCase) {
            under = under.substring(0, 1).toLowerCase() + under.substring(1);
        }
        return under + suffix;
    }

    /**
     * IService 解析
     *
     * @param tableAttribute    表对象
     * @param tableColAttribute 表属性对象
     * @return String
     */
    private static String iServiceParseCodeString(TableAttribute tableAttribute, TableColAttribute tableColAttribute) {
        StringBuilder sb = new StringBuilder();
        // 1.JAVA 文件引包
        sb.append("package " + packageIServicePath + ";\r\n");
        sb.append("\r\n");
        // 2.引入外部依赖包
        sb.append("import com.baomidou.mybatisplus.extension.service.IService;\r\n");
        String ent = "import " + packageEntityPath + "." + category(tableAttribute.getTableName(), true, "Entity");
        sb.append(ent + ";\r\n");

        // 3.注释
        sb.append("/**\r\n");
        sb.append(" * <ul>\r\n");
        sb.append(" * <li>name:  " + category(tableAttribute.getTableName(), true, "Service") + "</li>\r\n");
        sb.append(" * <li>author name: " + authorName + "</li>\r\n");
        sb.append(" * <li>create time: " + SDF.format(new Date()) + "</li>\r\n");
        sb.append(" * </ul>\r\n");
        sb.append(" */ \r\n");

        // 4.注解
        // 4.类与属性
        sb.append("public interface " + category(tableAttribute.getTableName(), true, "Service") + " extends IService<" + category(tableAttribute.getTableName(), true, "Entity") + "> {\r\n\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }


    /**
     * ServiceImpl 解析
     *
     * @param tableAttribute    表对象
     * @param tableColAttribute 表属性对象
     * @return String
     */
    private static String serviceImplParseCodeString(TableAttribute tableAttribute, TableColAttribute tableColAttribute) {
        StringBuilder sb = new StringBuilder();
        // 1.JAVA 文件引包
        sb.append("package " + packageServiceImplPath + ";\r\n");
        sb.append("\r\n");
        // 2.引入外部依赖包
        sb.append("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        String ent = "import " + packageEntityPath + "." + category(tableAttribute.getTableName(), true, "Entity");
        sb.append(ent + ";\r\n");
        String ma = "import " + packageMapperPath + "." + category(tableAttribute.getTableName(), true, "Mapper");
        sb.append(ma + ";\r\n");
        String ser = "import " + packageIServicePath + "." + category(tableAttribute.getTableName(), true, "Service");
        sb.append(ser + ";\r\n");

        // 3.注释
        sb.append("/**\r\n");
        sb.append(" * <ul>\r\n");
        sb.append(" * <li>name:  " + category(tableAttribute.getTableName(), true, "ServiceImpl") + "</li>\r\n");
        sb.append(" * <li>author name: " + authorName + "</li>\r\n");
        sb.append(" * <li>create time: " + SDF.format(new Date()) + "</li>\r\n");
        sb.append(" * </ul>\r\n");
        sb.append(" */ \r\n");

        // 4.注解
        sb.append("@Service\r\n");
        // 4.类与属性
        sb.append("public class " + category(tableAttribute.getTableName(), true, "ServiceImpl") + " extends ServiceImpl<" + category(tableAttribute.getTableName(), true, "Mapper") + ", " + category(tableAttribute.getTableName(), true, "Entity") + "> implements " + category(tableAttribute.getTableName(), true, "Service") + " {\r\n\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }


    /**
     * MAPPER 解析
     *
     * @param tableAttribute    表对象
     * @param tableColAttribute 表属性对象
     * @return String
     */
    private static String mapperParseCodeString(TableAttribute tableAttribute, TableColAttribute tableColAttribute) {
        StringBuilder sb = new StringBuilder();
        // 1.JAVA 文件引包
        sb.append("package " + packageMapperPath + ";\r\n");
        sb.append("\r\n");
        // 2.引入外部依赖包
        sb.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\r\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;\r\n");
        sb.append("import org.springframework.stereotype.Repository;\r\n");
        String ent = "import " + packageEntityPath + "." + category(tableAttribute.getTableName(), true, "Entity");
        sb.append(ent + ";\r\n");

        // 3.注释
        sb.append("/**\r\n");
        sb.append(" * <ul>\r\n");
        sb.append(" * <li>name:  " + category(tableAttribute.getTableName(), true, "Mapper") + "</li>\r\n");
        sb.append(" * <li>author name: " + authorName + "</li>\r\n");
        sb.append(" * <li>create time: " + SDF.format(new Date()) + "</li>\r\n");
        sb.append(" * </ul>\r\n");
        sb.append(" */ \r\n");

        // 4.注解
        sb.append("@Mapper\r\n");
        sb.append("@Repository\r\n");
        // 4.类与属性
        sb.append("public interface " + category(tableAttribute.getTableName(), true, "Mapper") + " extends BaseMapper<" + category(tableAttribute.getTableName(), true, "Entity") + "> {\r\n\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }


    /**
     * POJO 解析
     *
     * @param tableAttribute    表对象
     * @param tableColAttribute 表属性对象
     * @return String
     */
    private static String entityParseCodeString(TableAttribute tableAttribute, TableColAttribute tableColAttribute) {
        StringBuilder sb = new StringBuilder();

        // 1.JAVA 文件引包
        sb.append("package " + packageEntityPath + ";\r\n");
        sb.append("\r\n");

        // 2.引入外部依赖包
        // Mybatis-Plus TableName 注解类引入
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\r\n");


        for (int i = 0; i < tableColAttribute.getColName().length; i++) {
            String field = tableColAttribute.getColName()[i];
            // 证明存在字典字段
            if (field.indexOf("_") > -1) {
                sb.append("import com.baomidou.mybatisplus.annotation.TableField;\r\n");
                sb.append("import com.esrichina.services.annotation.DictAnnotation;\r\n");
                break;
            }
        }

        // Lombok 注解类引入
        sb.append("import lombok.Data;\r\n");
        // 判断此次表属性数据类型集合类中有哪些工具类引入
        // 数据类型去重，减少循环次数，免去重复导入包行数
        List<String> uniqList = new ArrayList<>(new HashSet<>(Arrays.asList(tableColAttribute.getColType())));

        System.out.println(JSONUtil.toJsonStr(uniqList));

        for (int i = 0; i < uniqList.size(); i++) {
            String colType = uniqList.get(i);

//            if(colType.indexOf("time zone") != -1){
//                colType = "timestamp without time zone";
//            }

            String rely = DataTypeConstant.getPackge(colType).getRely();
            if (rely != null && !"".equals(rely)) {
                sb.append(rely);
            }
        }

        // 3.注释
        sb.append("/**\r\n");
        sb.append(" * <ul>\r\n");
        sb.append(" * <li>table name:  " + tableAttribute.getTableName() + "</li>\r\n");
        sb.append(" * <li>table comment:  " + (tableAttribute.getTableComment() == null ? "" : tableAttribute.getTableComment()) + "</li>\r\n");
        sb.append(" * <li>author name: " + authorName + "</li>\r\n");
        sb.append(" * <li>create time: " + SDF.format(new Date()) + "</li>\r\n");
        sb.append(" * </ul>\r\n");
        sb.append(" */ \r\n");

        // 4.注解
        sb.append("@Data\r\n");
        sb.append("@TableName(\"" + tableAttribute.getTableName() + "\")\r\n");

        // 4.类与属性
        sb.append("public class " + category(tableAttribute.getTableName(), true, "Entity") + " {\r\n\r\n");
        // 属性
        sb.append(processAttrsAndComment(tableColAttribute));
        sb.append("\r\n");
        sb.append("}\r\n");
        return sb.toString();
    }

//    /**
//     * 解析文件代码字符串
//     *
//     * @param tableAttribute    表对象
//     * @param tableColAttribute 表属性对象
//     * @return String
//     */
//    private static String parse(TableAttribute tableAttribute, TableColAttribute tableColAttribute) {
//        return pojo(tableAttribute, tableColAttribute);
//    }

    /**
     * 变量及注释
     */
    private static String processAttrsAndComment(TableColAttribute tableColAttribute) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableColAttribute.getColName().length; i++) {

            if (tableColAttribute.getColName()[i].indexOf("........") == -1) {

                sb.append("\t/*" + tableColAttribute.getComment()[i] + " */\r\n");
                sb.append("\tprivate " + DataTypeConstant.getPackge(tableColAttribute.getColType()[i]).getType() + " " + tableColAttribute.getColName()[i] + ";\r\n");
                // 捕捉字典字典
                if (tableColAttribute.getColName()[i].indexOf("_") > -1) {
                    sb.append("\t/*" + tableColAttribute.getComment()[i] + "名称 */\r\n");
                    sb.append("\t@TableField(exist = false)\r\n");
                    sb.append("\t@DictAnnotation(value= \"sys_" + tableColAttribute.getColName()[i] + "\" , refField =\"" + tableColAttribute.getColName()[i] + "\")\r\n");
                    sb.append("\tprivate " + DataTypeConstant.getPackge(tableColAttribute.getColType()[i]).getType() + " " + tableColAttribute.getColName()[i] + "_name;\r\n");
                }

            }

        }
        return sb.toString();
    }

    /**
     * POJO文件生产位置
     */
    private static String packagePOJOPath() {
        String dirName = basePath + "/src/main/java/" + packageEntityPath.replace(".", "/");
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirName;
    }

    /**
     * MAPPER文件生产位置
     */
    private static String packageMapperPath() {
        String dirName = basePath + "/src/main/java/" + packageMapperPath.replace(".", "/");
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirName;
    }

    /**
     * ISERVICE文件生产位置
     */
    private static String packageIServicePath() {
        String dirName = basePath + "/src/main/java/" + packageIServicePath.replace(".", "/");
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirName;
    }

    /**
     * SERVICEIMPL文件生产位置
     */
    private static String packageIServiceImplPath() {
        String dirName = basePath + "/src/main/java/" + packageIServicePath.replace(".", "/") + "/Impl";
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirName;
    }


    /**
     * 功能： 核心方法
     */
    private void generate() {

        long st = System.currentTimeMillis();
        log.info(" MESSAGE : start db connection ... ");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 数据库连接
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
            if (connection != null) {
                log.info(" MESSAGE : db connection sucessed... ");
            } else {
                log.info(" MESSAGE : db connection failed... ");
                log.info(" MESSAGE : process stop ");
                return;
            }

            log.info(" MESSAGE : get db metadata... ");
            // 获取数据库的元数据
            DatabaseMetaData db = connection.getMetaData();

            // 表属性集合
            List<TableAttribute> tableAttributeList = new ArrayList<>();

            // 是否指定表
            if (generateTables == null) {
                // 从元数据中获取所有表
                ResultSet rs = db.getTables(null, null, null, new String[]{"TABLE"});
                while (rs.next()) {
                    TableAttribute tableAttribute = new TableAttribute();
                    // 表名称
                    tableAttribute.setTableName(rs.getString(TableConstant.TABLE_NAME.getCode()));
                    // 表注释
                    tableAttribute.setTableComment(rs.getString(TableConstant.REMARKS.getCode()));
                    tableAttributeList.add(tableAttribute);
                }
            } else {
                for (String tableName : generateTables) {
                    TableAttribute tableAttribute = new TableAttribute();
                    tableAttribute.setTableName(tableName);
                    tableAttributeList.add(tableAttribute);
                }
            }


            PrintWriter out = null;
            // 处理每一张表
            for (int j = 0; j < tableAttributeList.size(); j++) {
                TableAttribute tableAttribute = tableAttributeList.get(j);
                String tableName = tableAttribute.getTableName();
                String tableComment = tableAttribute.getTableComment();
                log.info(" MESSAGE : processing {} table", tableName);

                // 获取表设计属性SQL
                String tableAttributeSQL = tableAttributeSQL(tableName);
                if (tableAttributeSQL == null) {
                    log.info(" MESSAGE : process stop ... ");
                    return;
                }

                // 执行表设计属性SQL
                preparedStatement = connection.prepareStatement(tableAttributeSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet tableAttributeResultSet = preparedStatement.executeQuery();

                // 将光标定位到结果集中最后一行
                tableAttributeResultSet.last();
                // 得到当前行号
                int rowCount = tableAttributeResultSet.getRow();
                // 将光标定位到结果集中第一行之前
                tableAttributeResultSet.beforeFirst();

                // 初始化每张表设计属性
                TableColAttribute tableColAttribute = new TableColAttribute();
                tableColAttribute.setColName(new String[rowCount]);
                tableColAttribute.setColType(new String[rowCount]);
                tableColAttribute.setColSize(new int[rowCount]);
                tableColAttribute.setComment(new String[rowCount]);

                // 表设计属性集合赋值
                int index = 0;
                while (tableAttributeResultSet.next()) {
                    tableColAttribute.getColName()[index] = tableAttributeResultSet.getString("name");
                    tableColAttribute.getColType()[index] = tableAttributeResultSet.getString("type");
                    tableColAttribute.getColSize()[index] = tableAttributeResultSet.getInt("fields_length");
                    tableColAttribute.getComment()[index] = tableAttributeResultSet.getString("comment");
                    index++;
                }

                // 表主键SQL
                String tableAttributePKSQL = tableAttributePKSQL(tableName);

                // 执行表主键SQL
                preparedStatement = connection.prepareStatement(tableAttributePKSQL);
                ResultSet rspk = preparedStatement.executeQuery();

                // 主键赋值
                while (rspk.next()) {
                    tableColAttribute.setPk(rspk.getString("colname"));
                }


                // Entity
                //-----------------------------------------------------------
                // Entity 解析成 code string
                String entityString = entityParseCodeString(tableAttribute, tableColAttribute);
                // Entity 路径
                String entityDirName = CodeGenerator.packagePOJOPath();
                // 文件输出位置
                String entityPath = entityDirName + "/" + category(tableName, true, "Entity") + ".java";
                FileWriter efw = new FileWriter(entityPath);
                out = new PrintWriter(efw);
                out.println(entityString);
                out.flush();
                log.info(" MESSAGE : {}  ENTITY FILE CREATED ", entityPath);
                //-----------------------------------------------------------

                // Mapper
                //-----------------------------------------------------------
                String mapperString = mapperParseCodeString(tableAttribute, tableColAttribute);
                String mapperDirName = CodeGenerator.packageMapperPath();
                String mapperPath = mapperDirName + "/" + category(tableName, true, "Mapper") + ".java";
                FileWriter mfw = new FileWriter(mapperPath);
                out = new PrintWriter(mfw);
                out.println(mapperString);
                out.flush();
                log.info(" MESSAGE :  {}  MAPPER FILE CREATED ", mapperPath);
                // ----------------------------------------------------------

                // IService
                //-----------------------------------------------------------
                String iserviceString = iServiceParseCodeString(tableAttribute, tableColAttribute);
                String iservicerDirName = CodeGenerator.packageIServicePath();
                String iservicerPath = iservicerDirName + "/" + category(tableName, true, "Service") + ".java";
                FileWriter ifw = new FileWriter(iservicerPath);
                out = new PrintWriter(ifw);
                out.println(iserviceString);
                out.flush();
                log.info(" MESSAGE :  {}  SERVICE FILE CREATED ", iservicerPath);
                // ----------------------------------------------------------

                // ServiceImpl
                //-----------------------------------------------------------
                String serviceImplString = serviceImplParseCodeString(tableAttribute, tableColAttribute);
                String serviceImplDirName = CodeGenerator.packageIServiceImplPath();
                String serviceImplPath = serviceImplDirName + "/" + category(tableName, true, "ServiceImpl") + ".java";
                FileWriter lfw = new FileWriter(serviceImplPath);
                out = new PrintWriter(lfw);
                out.println(serviceImplString);
                out.flush();
                log.info(" MESSAGE :  {}  SERVICEIMPL FILE CREATED ", serviceImplPath);
                // ----------------------------------------------------------
            }
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            long ed = System.currentTimeMillis();
            log.info(" MESSAGE :  created is over ");
            log.info(" MESSAGE :  time : {} ms", ed - st);
        }
    }

}

@Data
class TableAttribute {
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;
}

@Data
class TableColAttribute {
    //主键
    private String pk;
    //列名数组
    private String[] colName;
    //列类型数组
    private String[] colType;
    //列存储大小数组
    private int[] colSize;
    // 列注释
    private String[] comment;

}