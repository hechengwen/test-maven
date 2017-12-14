package com.rmbbox.generator.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lxl on 15/9/23.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {

    private String columnName;
    private String dbType;
    private String javaType;
    private String columnComment;
    private String columnType;
    private String fieldName;
    private String keyJavaType;
    private boolean haveDate;

    public Column(String columnName, String dbType,String columnType,
                  String columnComment, String javaType) {
        this.columnName = columnName;
        if(GlobalConstant.MYSQL_DATATYPE_INT.equalsIgnoreCase(dbType)||GlobalConstant.MYSQL_DATATYPE_INTEGER.equalsIgnoreCase(dbType)){
            this.dbType = GlobalConstant.MYSQL_DATATYPE_INTEGER;
        }else if(GlobalConstant.MYSQL_DATATYPE_DATETIME.equalsIgnoreCase(dbType)){
            this.dbType=GlobalConstant.MYSQL_DATATYPE_TIMESTAMP;
        }else{
            this.dbType = dbType;
        }
        this.javaType = javaType;
        this.columnComment = columnComment;
        this.columnType = columnType;
    }
}
