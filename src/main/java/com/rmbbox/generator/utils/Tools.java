package com.rmbbox.generator.utils;

/**
 * Created by lxl on 15/9/23.
 */
public class Tools {
    /**
     * 根据SQL模板填入参数
     * */
    public static String getQuery(String sql, String[] parameter) {
        for (int i = 0; i < parameter.length; i++)
            sql = sql.replace("<[" + i + "]>", parameter[i]);
        return sql;
    }

    public static String getModelName(boolean cutTablePrefix,String tablePrefix,String tableName){
        tableName=tableName.toLowerCase();
        if(cutTablePrefix&&(tableName.startsWith(tablePrefix.toLowerCase())||tableName.startsWith(tablePrefix.toUpperCase()))){
            tableName=tableName.replaceFirst(tablePrefix.toLowerCase(), "");
        }
        String temp[]= tableName.split("_");
        tableName="";
        for (int i = 0; i < temp.length; i++) {
            if(temp[i].length()>0){
                tableName+=temp[i].substring(0, 1).toUpperCase()+temp[i].substring(1);
            }
        }
        return tableName;
    }

    public static String getFieldName(String columnName,boolean alias){
        columnName=columnName.toLowerCase();
        String temp[]= columnName.split("_");
        columnName="";
        for (int i = 0; i < temp.length; i++) {
            if(temp[i].length()>0){
                columnName+=temp[i].substring(0, 1).toUpperCase()+temp[i].substring(1);
            }
        }
        if(alias){
            columnName = columnName.substring(0, 1).toLowerCase() + columnName.substring(1);
        }
        return columnName;
    }
}
