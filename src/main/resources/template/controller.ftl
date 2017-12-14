package ${controllerUrl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cn.hcw.base.BaseController;
import javax.servlet.http.HttpServletResponse;
import ${serviceUrl}.${entityName}Service;
import org.springframework.ui.Model;
import java.util.Map;
import com.cn.hcw.common.PageInfo;
import com.cn.hcw.entity.${entityName};

@Controller
@RequestMapping("${alias}")
public class ${entityName}Controller extends BaseController{

    @Autowired
    ${entityName}Service ${alias}Service;

	@RequestMapping(value = "form")
	public String form(${entityName} ${alias}, Model model) {
		model.addAttribute("${alias}", ${alias});
		return "/${viewPrefix}/${alias}/form";
	}

	@RequestMapping(value = "save")
	public String save(${entityName} ${alias}) {
		${alias}Service.insert(${alias});
		return "redirect:/${alias}/";
	}

    /**
     * 列表页
     */
    @RequestMapping("/${alias}List")
    public String ${alias}List() throws Exception {
        return "/${viewPrefix}/${alias}/list-${alias}";
    }

    /**
     * ajax取列表信息
     */
    @ResponseBody
	@RequestMapping(value = {"query${entityName}ListByCondition",""})
	public String query${entityName}ListByCondition(@RequestParam(value = "iDisplayStart") Integer startRow,
                                         @RequestParam(value = "iDisplayLength") Integer pageSize,
                                         HttpServletResponse response) {
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(startRow, pageSize, Boolean.TRUE);
        this.pagerToJsonString(response, pageInfo);
        return null;
	}
}