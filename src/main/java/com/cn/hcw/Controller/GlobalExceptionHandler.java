package com.cn.hcw.Controller;

import com.cn.hcw.myexception.DemoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 该文件必须要和controller放在同一个目录下面，否者不起作用
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e.getMessage());
        mav.addObject("desc","全局异常都由我来处理");
        mav.addObject("url", req.getRequestURL());
        mav.addObject("contextUrl",req.getRequestURI());
        return mav;
    }

    @ExceptionHandler(DemoException.class)
    public ModelAndView demoException(HttpServletRequest req, DemoException e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("contextUrl",req.getRequestURI());
        mav.addObject("desc","全局异常都由我来处理");
        return mav;
    }
}
