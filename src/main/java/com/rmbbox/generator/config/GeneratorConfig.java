package com.rmbbox.generator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lxl on 15/9/23.
 *
 * 基础配置
 */
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorConfig {

    /** 项目路径(service)  */
    private String projectUrl;
    /** controller项目路径 */
    private String controllerProjectUrl;
    /** jsp项目路径 */
    private String viewPrefixUrl;
    /** 是否生成Entity文件存放位置 */
    private String entityUrl;
    /** 是否生成Dao文件存放位置 */
    private String daoUrl;
    /** 是否生成Service文件存放位置(impl包与service同级)  */
    private String serviceUrl;
    /** 是否生成Controller文件存放位置 */
    private String controllerUrl;
    /** 是否生成jsp文件存放位置 */
    private String viewPrefix;
    /** 是否生成SqlXml文件存放位置 */
    private String sqlXmlUrl;

    /** 是否要分页扩展 */
    private boolean paging;


    /** 是否映射所有表 */
    private boolean generatorAllTable;
    /** 需映射的表 */
    private String needGeneratorTables;


    /** 需要继承的baseController */
    private String baseController;

    /** 需要继承的baseService */
    private String baseService;


    /** 生成字段描述(注释) */
    private boolean createFieldDescribe;

    /** 生成的类是否去掉表名前缀 */
    private boolean cutTablePrefix;

    /** 表名前缀 */
    private String tablePrefix;


    /** 是否生成Entity */
    private boolean createEntity;
    /** 生成createDao */
    private boolean createDao;
    /** 生成Service*/
    private boolean createService;
    /** 生成ServiceImpl */
    private boolean createServiceImpl;
    /** 生成Controller */
    private boolean createController;
    /** 是否生成jsp */
    private boolean createView;
    /** 生成XMLMapper */
    private boolean createXMLMapper;

}
