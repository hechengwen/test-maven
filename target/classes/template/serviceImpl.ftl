package ${serviceUrl}.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ${daoUrl}.${entityName}Mapper;
import ${entityUrl}.${entityName};
import ${serviceUrl}.${entityName}Service;

@Service
public class ${entityName}ServiceImpl implements ${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${alias}Mapper;
    
    public int deleteByPrimaryKey(${keyJavaType} key) {
        return ${alias}Mapper.deleteByPrimaryKey(key);
    }
    
    public int insert(${entityName} record) {
        return ${alias}Mapper.insert(record);
    }
    
    public int insertSelective(${entityName} record) {
        return ${alias}Mapper.insertSelective(record);
    }

    public ${entityName} selectByPrimaryKey(${keyJavaType} key) {
        return ${alias}Mapper.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(${entityName} record) {
        return ${alias}Mapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(${entityName} record) {
        return ${alias}Mapper.updateByPrimaryKey(record);
    }
}