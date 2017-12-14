package com.rmbbox.generator.common;

/**
 * Created by lxl on 15/9/23.
 */
public class GlobalConstant {

    /** operate.tables 分隔符 ',' */
    public static String TABLES_SEGMENTATION_SYMBOL = ",";


    //------------------------------ORACLE-----------------

    /**按表名查询表的列信息 */
    public static String ORACLE_SELECT_COLUMN_BYTABLENAME = "select t.*,c.COMMENTS from user_tab_columns t,user_col_comments c where t.table_name = c.table_name and t.column_name = c.column_name and t.table_name = '<[0]>'";

    //------------------------------MYSQL-----------------

    /** mysql 数据类型	VARCHAR*/
    public static String MYSQL_DATATYPE_VARCHAR = "VARCHAR";

    /** mysql 数据类型	CHAR*/
    public static String MYSQL_DATATYPE_CHAR = "CHAR";

    /** mysql 数据类型	BLOB*/
    public static String MYSQL_DATATYPE_BLOB = "BLOB";

    /** mysql 数据类型	VARCHAR*/
    public static String MYSQL_DATATYPE_TEXT = "VARCHAR";

    /** mysql 数据类型	INTEGER*/
    public static String MYSQL_DATATYPE_INTEGER = "INTEGER";

    /** mysql 数据类型	INT*/
    public static String MYSQL_DATATYPE_INT = "INT";

    /** mysql 数据类型	SMALLINT*/
    public static String MYSQL_DATATYPE_SMALLINT = "SMALLINT";

    /** mysql 数据类型	MEDIUMINT*/
    public static String MYSQL_DATATYPE_MEDIUMINT = "MEDIUMINT";

    /** mysql 数据类型	BIT*/
    public static String MYSQL_DATATYPE_BIT = "BIT";

    /** mysql 数据类型	BIGINT*/
    public static String MYSQL_DATATYPE_BIGINT = "BIGINT";

    /** mysql 数据类型	FLOAT*/
    public static String MYSQL_DATATYPE_FLOAT = "FLOAT";

    /** mysql 数据类型	DOUBLE*/
    public static String MYSQL_DATATYPE_DOUBLE = "DOUBLE";

    /** mysql 数据类型	DECIMAL*/
    public static String MYSQL_DATATYPE_DECIMAL = "DECIMAL";

    /** mysql 数据类型	BOOLEAN*/
    public static String MYSQL_DATATYPE_BOOLEAN = "BOOLEAN";

    /** mysql 数据类型	DATE*/
    public static String MYSQL_DATATYPE_DATE = "DATE";

    /** mysql 数据类型	TIME*/
    public static String MYSQL_DATATYPE_TIME = "TIME";

    /** mysql 数据类型	DATETIME*/
    public static String MYSQL_DATATYPE_DATETIME = "DATETIME";

    /** mysql 数据类型	TIMESTAMP*/
    public static String MYSQL_DATATYPE_TIMESTAMP = "TIMESTAMP";

    /** mysql 数据类型	TINYINT*/
    public static String MYSQL_DATATYPE_TINYINT = "TINYINT";

    /** mysql 数据类型	TINYBLOB*/
    public static String MYSQL_DATATYPE_TINYBLOB = "TINYBLOB";

    /** mysql 数据类型	TINYTEXT*/
    public static String MYSQL_DATATYPE_TINYTEXT = "TINYTEXT";

    /** mysql 数据类型	ENUM*/
    public static String MYSQL_DATATYPE_ENUM = "ENUM";

    /** mysql 数据类型	SET*/
    public static String MYSQL_DATATYPE_SET = "SET";

    /** mysql 数据类型	BINARY*/
    public static String MYSQL_DATATYPE_BINARY = "BINARY";

    /** mysql 数据类型	YEAR*/
    public static String MYSQL_DATATYPE_YEAR = "YEAR";

    /** mysql 数据类型	VARBINARY*/
    public static String MYSQL_DATATYPE_VARBINARY = "VARBINARY";

    /** mysql 数据类型	LINESTRING*/
    public static String MYSQL_DATATYPE_LINESTRING = "LINESTRING";

    /** mysql 数据类型	POLYGON*/
    public static String MYSQL_DATATYPE_POLYGON = "POLYGON";

    /** mysql 数据类型	POINT*/
    public static String MYSQL_DATATYPE_POINT = "POINT";

    /** mysql 数据类型	GEOMETRY*/
    public static String MYSQL_DATATYPE_GEOMETRY = "GEOMETRY";

    /** mysql 数据类型	MULTIPOINT*/
    public static String MYSQL_DATATYPE_MULTIPOINT = "MULTIPOINT";

    /** mysql 数据类型	MULTILINESTRING*/
    public static String MYSQL_DATATYPE_MULTILINESTRING = "MULTILINESTRING";

    /** mysql 数据类型	MULTIPOLYGON*/
    public static String MYSQL_DATATYPE_MULTIPOLYGON = "MULTIPOLYGON";

    /** mysql 数据类型	GEOMETRYCOLLECTION*/
    public static String MYSQL_DATATYPE_GEOMETRYCOLLECTION = "GEOMETRYCOLLECTION";

    /** mysql 数据类型	LONGTEXT*/
    public static String MYSQL_DATATYPE_LONGTEXT = "LONGTEXT";

    /** mysql 数据类型	MEDIUMTEXT*/
    public static String MYSQL_DATATYPE_MEDIUMTEXT = "MEDIUMTEXT";

    /** mysql 列类型：主键	PRI*/
    public static String MYSQL_COLUMN_TYPE_PRI = "PRI";


    /**按表名查询表的列信息 */
    public static String MYSQL_SELECT_COLUMN_BYTABLENAME = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = '<[0]>' and table_schema = '<[1]>'";

    /**按数据库名查询所有表*/
    public static String MYSQL_SELECT_TABLES_BYDBNAME = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE lower(TABLE_SCHEMA)=lower('<[0]>')";

    //------------------------JAVA	DATETYPE--------------------------
    /** java 数据类型	String*/
    public static String JAVA_DATATYPE_STRING = "String";

    /** java 数据类型	Byte*/
    public static String JAVA_DATATYPE_BATE = "Byte";

    /** java 数据类型	Long*/
    public static String JAVA_DATATYPE_LONG = "Long";

    /** java 数据类型	Long*/
    public static String JAVA_DATATYPE_DECIMAL = "BigDecimal";

    /** java 数据类型	Integer*/
    public static String JAVA_DATATYPE_INTEGER = "Integer";

    /** java 数据类型	Boolean*/
    public static String JAVA_DATATYPE_BOOLEAN = "Boolean";

    /** java 数据类型	Short*/
    public static String JAVA_DATATYPE_SHORT = "Short";

    /** java 数据类型	Double*/
    public static String JAVA_DATATYPE_DOUBLE = "Double";

    /** java 数据类型	Float*/
    public static String JAVA_DATATYPE_FLOAT = "Float";

    /** java 数据类型	Date*/
    public static String JAVA_DATATYPE_DATE = "Date";

    /** java 数据类型	Object*/
    public static String JAVA_DATATYPE_OBJECT = "Object";
//
//	/** java 数据类型	BigInteger*/
//	public static String JAVA_DATATYPE_BIGINTEGER = "BigInteger";

}
