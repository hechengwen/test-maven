package ${daoUrl};

import ${entityUrl}.${entityName};
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ${entityName}Mapper {

    int deleteByPrimaryKey(${keyJavaType} key);

    int insert(${entityName} record);

    int insertSelective(${entityName} record);

    ${entityName} selectByPrimaryKey(${keyJavaType} key);

    int updateByPrimaryKeySelective(@Param("record")${entityName} record);

    int updateByPrimaryKey(@Param("record")${entityName} record);

}