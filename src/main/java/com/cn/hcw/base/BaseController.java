package com.cn.hcw.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by hcw on 2017/3/30 0030.
 */
public class BaseController {

    protected HttpServletRequest request;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 为response添加头信息和内容类型信息，便于下载csv文件
     *
     * @param response
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public HttpServletResponse modifyResponseForCSV(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setCharacterEncoding("GBK");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=\""
                + new String(fileName.getBytes("GBK"), "ISO8859-1")+"\"");//兼容firefox文件名包括空格字符

        return response;
    }
}
