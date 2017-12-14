package com.cn.hcw.Controller;


import com.cn.hcw.base.BaseController;
import com.cn.hcw.entity.CoreUser;
import com.cn.hcw.initconfig.Config;
import com.cn.hcw.initconfig.InitBean;
import com.cn.hcw.initconfig.InitConfig;
import com.cn.hcw.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/9 0009.
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    @Autowired
    CoreUserService coreUserService;

    @Autowired
    InitConfig initConfig;

    @RequestMapping("/index")
    public ModelAndView toIndex(HttpServletRequest request, RedirectAttributes attributes) throws Exception{
        Map<String,Object> map = InitBean.getInitMap();
        Config config = (Config) map.get("config");
        String url = config.getUrl();
        String data = config.getData();
        attributes.addFlashAttribute("url",url);
        attributes.addFlashAttribute("data",data);
        ModelAndView modelAndView = new ModelAndView("redirect:/login/index");
        CoreUser coreUser = coreUserService.getUserById("1");
        modelAndView.addObject("coreUser",coreUser);

//        String jsonObject = initConfig.send();
//        modelAndView.addObject("json",jsonObject);

        return modelAndView;
    }



}
