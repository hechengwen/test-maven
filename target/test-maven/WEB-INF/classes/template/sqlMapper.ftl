<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${daoUrl}.${entityName}Mapper" >
    <resultMap id="BaseResultMap" type="${entityUrl}.${entityName}" >
    <#list columns as c>
        <#if c.columnType=="PRI">
            <id column="${c.columnName}" property="${c.fieldName}" jdbcType="${c.dbType?upper_case}" />
        <#else >
            <result column="${c.columnName}" property="${c.fieldName}" jdbcType="${c.dbType?upper_case}" />
        </#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list columns as c>${c.columnName}<#if (c_index+1)!=(columns?size)>,</#if><#if (c_index+1)%10==0>${'\n\t\t'}</#if></#list>
    </sql>

    <select id="selectByPrimaryKey" parameterType="java.lang.${keyJavaType}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where ${keyColumnName} = ${'#{'}${keyFieldName},jdbcType=${keyDBType?upper_case}}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.${keyJavaType}">
    delete from ${tableName}
    where ${keyColumnName} = ${'#{'}${keyFieldName},jdbcType=${keyDBType?upper_case}}
  </delete>

  <insert id="insert" parameterType="${entityUrl}.${entityName}">
    <selectKey resultType="java.lang.${keyJavaType}" order="BEFORE" keyProperty="${keyFieldName}">
        SELECT UPPER(UUID()) AS ${keyFieldName}
    </selectKey>
    insert into ${tableName} (<#list columns as c>${c.columnName}<#if (c_index+1)!=(columns?size)>,</#if><#if (c_index+1)%3==0>${'\n\t\t'}</#if></#list>)
    values (<#list columns as c>${'#{'}${c.fieldName},jdbcType=${c.dbType?upper_case}}<#if (c_index+1)!=(columns?size)>,</#if><#if (c_index+1)%3==0>${'\n\t\t'}</#if></#list>)
  </insert>
  <insert id="insertSelective" parameterType="${entityUrl}.${entityName}">
        <selectKey resultType="java.lang.${keyJavaType}" order="BEFORE" keyProperty="${keyFieldName}">
            SELECT UPPER(UUID()) AS ${keyFieldName}
        </selectKey>
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list columns as c>
      <if test="${c.fieldName} != null" >
          ${c.columnName}<#if (c_index+1)!=(columns?size)>,</#if>
      </if>
    </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
  	<#list columns as c>
      <if test="${c.fieldName} != null" >
          ${'#{'}${c.fieldName},jdbcType=${c.dbType?upper_case}}<#if (c_index+1)!=(columns?size)>,</#if>
      </if>
    </#list>
    </trim>
  </insert>

  <update id="updateByPrimaryKeySelective" parameterType="${entityUrl}.${entityName}">
    update ${tableName}
    <set>
    <#list columns as c>
    <#if c.columnType!="PRI">
    <if test="record.${c.fieldName} != null">
        ${c.columnName} = ${'#{'}record.${c.fieldName},jdbcType=${c.dbType?upper_case}}<#if (c_index+1)!=(columns?size)>,</#if>
    </if>
    </#if>
    </#list>
    </set>
    where ${keyColumnName} = ${'#{'}record.${keyFieldName},jdbcType=${keyDBType?upper_case}}
  </update>
  <update id="updateByPrimaryKey" parameterType="${entityUrl}.${entityName}">
    update ${tableName}
    set <#list columns as c><#if c.columnType!="PRI">${c.columnName} = ${'#{'}record.${c.fieldName},jdbcType=${c.dbType?upper_case}}<#if (c_index+1)!=(columns?size)>,</#if><#if (c_index+1)%1==0>${'\n\t\t'}</#if></#if></#list>
    where ${keyColumnName} = ${'#{'}record.${keyFieldName},jdbcType=${keyDBType?upper_case}}
  </update>
</mapper>