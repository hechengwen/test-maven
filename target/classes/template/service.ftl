package ${serviceUrl};

import ${entityUrl}.${entityName};

import java.util.List;

public interface ${entityName}Service {

    int deleteByPrimaryKey(${keyJavaType} key);

    int insert(${entityName} record);

    int insertSelective(${entityName} record);

    ${entityName} selectByPrimaryKey(${keyJavaType} key);

    int updateByPrimaryKeySelective(${entityName} record);

    int updateByPrimaryKey(${entityName} record);

}