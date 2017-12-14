package com.cn.hcw.Controller;

import com.cn.hcw.base.BaseController;
import com.cn.hcw.restful.RestData;
import com.cn.hcw.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lenovo on 2017/3/30 0030.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public ModelAndView index(@RequestParam(value = "loginName",required = false)String loginName,
                              @RequestParam(value = "password",required = false)String password,
                              @ModelAttribute("url") String url,@ModelAttribute("data")String data,Model model3){
        ModelAndView model = new ModelAndView("/index");
        logger.info("重定向参数为：url={},data={}",url,data);
        RestData restData = loginService.userLogin(loginName,password);
        if(restData.getSuccess() == 0){
            return model;
        }
        model = new ModelAndView("/success");
        model.addObject("loginName",loginName);
        return model;
    }

}
