package com.rmbbox.generator.utils;

import com.rmbbox.generator.common.GlobalConstant;

/**
 * Created by lxl on 15/9/24.
 */
public class DB2JavaImpl implements DB2Java {
    @Override
    public String typeConvertByMYSQL(String type) {
        type=type.trim();
        if(!(type.length()>0))
            return "";

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_INT)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_INTEGER)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_MEDIUMINT))
            return GlobalConstant.JAVA_DATATYPE_INTEGER;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_TINYINT))
//            return GlobalConstant.JAVA_DATATYPE_BATE;
            return GlobalConstant.JAVA_DATATYPE_BOOLEAN;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_SMALLINT))
            return GlobalConstant.JAVA_DATATYPE_SHORT;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_BIGINT)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_DECIMAL))
//            return GlobalConstant.JAVA_DATATYPE_LONG;
            return GlobalConstant.JAVA_DATATYPE_DECIMAL;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_CHAR)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_VARCHAR)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_TINYTEXT)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_ENUM)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_SET))
            return GlobalConstant.JAVA_DATATYPE_STRING;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_DATE)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_DATETIME)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_TIMESTAMP)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_YEAR)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_TIME))
            return GlobalConstant.JAVA_DATATYPE_DATE;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_FLOAT))
            return GlobalConstant.JAVA_DATATYPE_FLOAT;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_BIT))
            return GlobalConstant.JAVA_DATATYPE_BOOLEAN;

        if(type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_BIT)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_GEOMETRYCOLLECTION)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_MULTIPOLYGON)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_MULTILINESTRING)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_MULTIPOINT)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_POLYGON)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_LINESTRING)||type.equalsIgnoreCase(GlobalConstant.MYSQL_DATATYPE_POINT))
            return GlobalConstant.JAVA_DATATYPE_OBJECT;

        return GlobalConstant.JAVA_DATATYPE_OBJECT;
    }

    @Override
    public String typeConvertByORACLE(String type) {
        return null;
    }

    @Override
    public String typeConvertByDB2(String type) {
        return null;
    }

    @Override
    public String typeConvertByMSSQL(String type) {
        return null;
    }
}
