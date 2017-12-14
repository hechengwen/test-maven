package ${entityUrl};

<#if haveDate>
import java.util.Date;
</#if>
<#if haveBigDecimal>
import java.math.BigDecimal;
</#if>
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import com.cn.hcw.base.BaseObject;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ${entityName} extends BaseObject {

<#if columns?exists>
<#list columns as c>
    <#if createFieldDescribe>/** ${c.columnComment} */</#if>
    private ${c.javaType} ${c.fieldName};

</#list>
</#if>

}