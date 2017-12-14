package com.rmbbox;

import com.rmbbox.generator.Generator;
import com.rmbbox.generator.GeneratorDefaultImpl;
import com.rmbbox.generator.utils.DB2Java;
import com.rmbbox.generator.utils.DB2JavaImpl;

/**
 * Created by lxl on 15/9/23.
 *@author 李小龙
 *
 * 执行main方法就可以生成代码了
 * 配置文件在resources里面
 * example是示例配置，程序加载的是generatorConfig.xml
 * db.properties配置数据库连接信息
 * sql文件夹里有测试脚本
 *
 * DB2Java是数据转换工具，比如数据库的tinvint转成boolean就是在这接口里实现
 * 数据库字段的类型需要转换如datetime类型需要转换成timestamp因为org.apache.ibatis.type.JdbcType枚举里没有datetime
 *
 * 提示*:时间有限代码可能有些细节有瑕疵
 *          目前测试了增删改查都没问题，service-all里有个GeneratorTest是使用示例
 *
 *          可以在freemarker模板文件里进行简单地修改，如果有BUG或疑问请联系我
 */
public class MainApp {

    /**
     * 生成代码
     */
    public static void main(String... args) throws Exception {

        //类型转换规则(目前只实现了MySql)
        DB2Java db2Java = new DB2JavaImpl();

        Generator generator = new GeneratorDefaultImpl().loadDB2Java(db2Java);
        //生成
        generator.generator();
    }


}