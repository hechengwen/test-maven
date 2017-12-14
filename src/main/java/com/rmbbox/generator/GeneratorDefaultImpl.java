package com.rmbbox.generator;

import com.rmbbox.generator.common.Column;
import com.rmbbox.generator.common.GlobalConstant;
import com.rmbbox.generator.config.DBConfig;
import com.rmbbox.generator.config.GeneratorConfig;
import com.rmbbox.generator.exception.ConfigException;
import com.rmbbox.generator.exception.DBException;
import com.rmbbox.generator.utils.DB2Java;
import com.rmbbox.generator.utils.FileUtil;
import com.rmbbox.generator.utils.StringUtil;
import com.rmbbox.generator.utils.Tools;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.*;

/**
 * Created by lxl on 15/9/23.
 *
 * 代码生成的默认实现类
 * @author 李小龙
 */
public class GeneratorDefaultImpl implements Generator {

    //基本配置
    private GeneratorConfig generatorConfig;
    //数据库配置
    private DBConfig dbConfig;
    //需要映射的表
    private Map<String, List<Column>> tables = new HashMap();

    private DB2Java db2Java;

    private UrlInfo urlInfo = new UrlInfo();

    public GeneratorDefaultImpl loadDB2Java(DB2Java db2Java) throws ConfigException {
        if(db2Java==null){
            throw new ConfigException("加载类型转换工具失败!");
        }
        this.db2Java = db2Java;
        return this;
    }

    /**
     * 解析配置文件XML，加载进内存里
     * */
    private GeneratorConfig getGeneratorConfig() throws JAXBException, URISyntaxException {
        System.out.println("加载生成配置...");
        if(generatorConfig!=null){
            return generatorConfig;
        }
        JAXBContext context = JAXBContext.newInstance(GeneratorConfig.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//        generatorConfig = (GeneratorConfig) unmarshaller.unmarshal(new StringReader(FileUtil.readTxtFile(new File("/Users/lxl/project/web/rmbbox-generator/src/main/resources/generatorConfig.xml"))));
        generatorConfig = (GeneratorConfig) unmarshaller.unmarshal(new StringReader(FileUtil.readTxtFile(new File(GeneratorDefaultImpl.class.getClassLoader().getResource("generatorConfig.xml").getPath()))));
        System.out.println("加载生成配置完成!");
        return generatorConfig;
    }

    /**
     * 加载数据库配置到内存里
     * */
    private DBConfig getDBConfig() throws IOException, DBException, URISyntaxException {
        System.out.println("加载数据库配置...");
        if(dbConfig!=null){
            return dbConfig;
        }
        dbConfig = new DBConfig();
        Properties prop = new PropertyUtil().loadProperty("/db.properties");
        dbConfig.setDriver(prop.getProperty("jdbc.driver"));
        dbConfig.setUrl(prop.getProperty("jdbc.url"));
        dbConfig.setUsername(prop.getProperty("jdbc.username"));
        dbConfig.setPassword(prop.getProperty("jdbc.password"));
        if(StringUtils.isEmpty(dbConfig.getDriver())||
           StringUtils.isEmpty(dbConfig.getUrl())||
           StringUtils.isEmpty(dbConfig.getUsername())||
           StringUtils.isEmpty(dbConfig.getPassword())){
            throw new DBException("连接数据库配置异常!");
        }
        System.out.println("加载数据库配置完成!");
        return dbConfig;
    }

    /**
     * 生成代码
     * */
    public void generator() throws Exception {
        //先做准备工作，加载生成配置、数据库配置
        prepare();
        //生成代码
        createFiles();
    }

    /**
     * 准备工作
     * */
    private void prepare() throws Exception {
        System.out.println("开始准备工作...");
        getGeneratorConfig();
        getDBConfig();
        getTablesInfo();
        loadUrlInfo();
        createFolder();
        System.out.println("准备工作完成!");
    }

    private void loadUrlInfo(){
        System.out.println("加载路径信息...");
        urlInfo.controllerProjectUrl = generatorConfig.getControllerProjectUrl()+"/src/main/java/";
        urlInfo.projectUrl = generatorConfig.getProjectUrl()+"/src/main/java/";
        urlInfo.entityUrl=urlInfo.projectUrl+generatorConfig.getEntityUrl().replaceAll("\\.", "/");
        urlInfo.daoUrl=urlInfo.projectUrl+generatorConfig.getDaoUrl().replaceAll("\\.", "/");
        urlInfo.serviceUrl=urlInfo.projectUrl+generatorConfig.getServiceUrl().replaceAll("\\.", "/");
        urlInfo.serviceImplUrl = urlInfo.serviceUrl+"/impl";
        urlInfo.controllerUrl=urlInfo.controllerProjectUrl+generatorConfig.getControllerUrl().replaceAll("\\.", "/");
        urlInfo.viewUrl=generatorConfig.getControllerProjectUrl()+generatorConfig.getViewPrefixUrl()+generatorConfig.getViewPrefix();
        urlInfo.sqlXmlUrl=generatorConfig.getProjectUrl()+"/src/main/resources/"+generatorConfig.getSqlXmlUrl().replaceAll("\\.", "/");
        System.out.println("加载路径信息完成!");
    }
    /**
     * 创建文件夹
     * */
    private void createFolder() {
        System.out.println("创建文件夹...");
        FileUtil.createFolders(new String[]{urlInfo.entityUrl, urlInfo.daoUrl, urlInfo.serviceUrl,urlInfo.serviceImplUrl,urlInfo.controllerUrl,urlInfo.viewUrl,urlInfo.sqlXmlUrl});
        System.out.println("创建文件夹完成!");
    }

    /**
     * 生成文件
     * */
    private void createFiles() throws Exception {
        //遍历需要映射的表
        Set entrySet = tables.entrySet();
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o ;
            if(generatorConfig.isCreateController())
                createFile(entry.getKey().toString(),"controller.ftl");
            if(generatorConfig.isCreateView()) {
                createFile(entry.getKey().toString(), "viewForm.ftl");
                createFile(entry.getKey().toString(), "viewList.ftl");
            }
            if(generatorConfig.isCreateDao())
                createFile(entry.getKey().toString(),"dao.ftl");
            if(generatorConfig.isCreateEntity()){
                createFile(entry.getKey().toString(),"entity.ftl");
            }
            if(generatorConfig.isCreateServiceImpl())
                createFile(entry.getKey().toString(),"serviceImpl.ftl");
            if(generatorConfig.isCreateService())
                createFile(entry.getKey().toString(),"service.ftl");
            if(generatorConfig.isCreateXMLMapper())
                createFile(entry.getKey().toString(),"sqlMapper.ftl");
        }
    }

    private void createFile(String tableName,String ftl) throws Exception {
        String entityName = Tools.getModelName(generatorConfig.isCutTablePrefix(), generatorConfig.getTablePrefix(), tableName);
        String alias = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
        String keyJavaType="";
        String keyFieldName="";
        String keyColumnName="";
        String keyDBType="";
        boolean haveDate = false;
        boolean haveBigDecimal = false;
        for (Column column : tables.get(tableName)) {
            //如果要支持其他数据库，这里需要加判断
            column.setJavaType(db2Java.typeConvertByMYSQL((column.getDbType())));
            column.setFieldName(Tools.getFieldName(column.getColumnName(),true));
            if (GlobalConstant.MYSQL_COLUMN_TYPE_PRI.equalsIgnoreCase(column.getColumnType())) {
                keyJavaType = db2Java.typeConvertByMYSQL(column.getDbType());
                keyFieldName = column.getFieldName();
                keyColumnName = column.getColumnName();
                keyDBType = column.getDbType();
            }
            if (db2Java.typeConvertByMYSQL(column.getDbType()).equals(GlobalConstant.JAVA_DATATYPE_DATE)) {
                haveDate = true;
            }
            if (db2Java.typeConvertByMYSQL(column.getDbType()).equals(GlobalConstant.JAVA_DATATYPE_DECIMAL)) {
                haveBigDecimal = true;
            }
        }

        //freemarker模板所需数据
        Map<String, Object> root = new HashMap();
        //实体类 包路径
        root.put("entityUrl", generatorConfig.getEntityUrl());
        //dao 包路径
        root.put("daoUrl", generatorConfig.getDaoUrl());
        //service 包路径
        root.put("serviceUrl", generatorConfig.getServiceUrl());
        //controller 包路径
        root.put("controllerUrl", generatorConfig.getControllerUrl());
        //jsp 路径
        root.put("viewPrefix", generatorConfig.getViewPrefix());
        //sqlXML 包路径
        root.put("sqlXmlUrl", generatorConfig.getSqlXmlUrl());
        //实体类 类名
        root.put("entityName", entityName);
        //表名
        root.put("tableName", tableName);
        //表名别称(首字母小写)
        root.put("alias", alias);
        //主键数据库类型
        root.put("keyDBType", keyDBType);
        //主键Java类型
        root.put("keyJavaType", keyJavaType);
        //主键字段名
        root.put("keyFieldName", keyFieldName);
        //主键字段名
        root.put("keyColumnName", keyColumnName);
        //实体类是否有时间属性
        root.put("haveDate", haveDate);
        //实体类是否有时间属性
        root.put("haveBigDecimal", haveBigDecimal);
        //属性
        root.put("columns", tables.get(tableName));
        //是否分页
        root.put("paging", generatorConfig.isPaging());
        //是否在实体类增加字段描述
        root.put("createFieldDescribe", generatorConfig.isCreateFieldDescribe());

        String path="";
        if("controller.ftl".equals(ftl))
            path=urlInfo.controllerUrl+"/"+entityName+"MGController.java";
        if("viewForm.ftl".equals(ftl)) {
            FileUtil.createFolders(new String[]{urlInfo.viewUrl+"/" + alias});
            path = urlInfo.viewUrl + "/" + alias + "/" + "form.jsp";
        }
        if("viewList.ftl".equals(ftl)){
            FileUtil.createFolders(new String[]{urlInfo.viewUrl+"/" + alias});
            path=urlInfo.viewUrl + "/" + alias+"/list-"+alias+".jsp";
        }
        if("dao.ftl".equals(ftl))
            path=urlInfo.daoUrl+"/"+entityName+"Mapper.java";
        if("entity.ftl".equals(ftl))
            path=urlInfo.entityUrl+"/"+entityName+".java";
        if("service.ftl".equals(ftl))
            path=urlInfo.serviceUrl+"/"+entityName+"Service.java";
        if("serviceImpl.ftl".equals(ftl))
            path=urlInfo.serviceUrl+"/impl/"+entityName+"ServiceImpl.java";
        if("sqlMapper.ftl".equals(ftl))
            path=urlInfo.sqlXmlUrl+"/"+entityName+"Mapper.xml";

        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
//        cfg.setDirectoryForTemplateLoading(new File("/Users/lxl/project/web/rmbbox-generator/src/main/resources/template"));
        cfg.setDirectoryForTemplateLoading(new File(GeneratorDefaultImpl.class.getClassLoader().getResource("").getPath()+"/template"));
        Template template = cfg.getTemplate(ftl);
//        Writer out = new OutputStreamWriter(System.out);
        StringWriter out = new StringWriter();
        template.process(root, out);
        FileUtil.writeTxtFile(out.toString(),new File(path));
    }

    void getTablesInfo() throws SQLException, ClassNotFoundException, ConfigException {
        System.out.println("加载需要映射的表的信息...");
        String[] tbs = null;
        if(!generatorConfig.isGeneratorAllTable()){
            tbs = generatorConfig.getNeedGeneratorTables().split(GlobalConstant.TABLES_SEGMENTATION_SYMBOL);
        }else {
            // 根据数据库名获取所有表
            List<Object[]> list = new DBUtil().executeSQL(Tools.getQuery(GlobalConstant.MYSQL_SELECT_TABLES_BYDBNAME, new String[]{dbConfig.getUrl().substring(dbConfig.getUrl().lastIndexOf("/")+1)}));
            if (list != null && list.size() > 0) {
                String tempTables[] = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    tempTables[i] = StringUtil.convert(list.get(i)[0]);
                }
                tbs = tempTables;
            }
        }
        if(tbs==null){
            throw new ConfigException("配置异常");
        }
        for (int i = 0; i < tbs.length; i++) {
            List<Column> tempColumn = new ArrayList<Column>();
            List<Object[]> list = new DBUtil().executeSQL(Tools.getQuery(GlobalConstant.MYSQL_SELECT_COLUMN_BYTABLENAME, new String[]{tbs[i],dbConfig.getUrl().substring(dbConfig.getUrl().lastIndexOf("/")+1)}));
            if (list != null && list.size() > 0) {
                for (Object[] objects : list) {
                    tempColumn.add(new Column(StringUtil.convert(objects[0]), StringUtil.convert(objects[1]), StringUtil.convert(objects[2]), StringUtil.convert(objects[3]), ""));
                }
            }
            tables.put(tbs[i], tempColumn);
        }
        System.out.println("加载需要映射的表的信息完成!");
    }

    /**
     * 测试数据库连接
     * */
    public boolean testDBConnect() throws Exception {
        prepare();
        return true;
    }


    class DBUtil{
        private Connection conn=null;
        private Statement stmt =null;
        private ResultSet rs =null;

        private void openConn() throws ClassNotFoundException, SQLException {
            Class.forName(dbConfig.getDriver());
            conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        }

        private void closeConn() throws SQLException {
            if(rs!=null)
                rs.close();
            if(stmt!=null)
                stmt.close();
            if(conn!=null||!conn.isClosed())
                conn.close();
        }

        public List<Object []> executeSQL(String sql) throws SQLException, ClassNotFoundException {
            List<Object []> list= new ArrayList();
            openConn();
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while(rs.next()){
                    Object[] obj = new Object[columnCount];
                    for (int i = 0; i < obj.length; i++) {
                        obj[i]=rs.getString(i+1);
                    }
                    list.add(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                closeConn();
            }
            return list;
        }

    }

    class PropertyUtil{
        public Properties loadProperty(String pathMail) throws IOException, URISyntaxException {
            File clsFile = new File(GeneratorDefaultImpl.class.getResource("/").toURI());
            Properties mail = new Properties();
            InputStream inMail = new FileInputStream(new File(clsFile, "").getCanonicalPath() + pathMail);
            try {
                mail.load(inMail);
                return mail;
            } catch (IOException e) {
                throw new ExceptionInInitializerError("不能正确读取资源文件");
            } finally {
                if (inMail != null) {
                    try {
                        inMail.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class UrlInfo{
        String controllerProjectUrl;
        String viewUrl;
        String projectUrl;
        String entityUrl;
        String daoUrl;
        String serviceUrl;
        String serviceImplUrl;
        String controllerUrl;
        String sqlXmlUrl;
    }
}
