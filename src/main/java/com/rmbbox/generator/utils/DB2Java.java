package com.rmbbox.generator.utils;

/**
 * Created by lxl on 15/9/24.
 */
public interface DB2Java {

    String typeConvertByMYSQL(String type);

    String typeConvertByORACLE(String type);

    String typeConvertByDB2(String type);

    String typeConvertByMSSQL(String type);
}
