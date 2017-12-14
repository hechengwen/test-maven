package com.cn.hcw.Controller;

import com.cn.hcw.base.BaseController;
import com.cn.hcw.file.GeneratePDF;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.ImagingOpException;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/5/2 0002
 * Description:
 * Others:
 */
@Controller
@RequestMapping("download")
public class CSVDownLoadController extends BaseController{

    @RequestMapping(value = "testDownLoad",method = RequestMethod.GET)
    public void downLoad(HttpServletResponse response) throws Exception{
        String fileName = "test_down_load.csv";
        StringBuilder sb = new StringBuilder();
        sb.append("登录名").append(",").append("姓名").append(",").append("状态");
        OutputStream outputStream = null;
        BufferedWriter bw = null;
        try {
            outputStream = super.modifyResponseForCSV(response,fileName).getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(outputStream,"GBK"));
            bw.append(sb.toString());
            bw.newLine();
            Student s1 = new Student("feifei","feifei","good time");
            Student s2 = new Student("daidai","daidai","bad time");
            Student s3 = new Student("kuku","kuku","cry time");
            List<Student> list = new ArrayList();
            list.add(s1);list.add(s2);list.add(s3);
            for (Student s : list){
                StringBuilder stb = new StringBuilder();
                stb.append(s.getLoginName()).append(",").append(s.getName()).append(",").append(s.getStatus());
                bw.append(stb.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e){
            logger.info(e.toString());
        } finally {
            if (null != outputStream){
                outputStream.close();
            }
            if (null != bw){
                bw.close();
            }
        }
    }

    @Data
    @AllArgsConstructor
    class Student{
        private String loginName;
        private String name;
        private String status;
    }

    @RequestMapping(value = "downLoadUser",method = RequestMethod.GET)
    public void downLoadUser(HttpServletResponse response){
        String fileName = "user.csv";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("userName").append(",").append("userPassword").append(",").append("userMobile");
        OutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = super.modifyResponseForCSV(response,fileName).getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"GBK"));
            bufferedWriter.append(stringBuilder.toString());
            bufferedWriter.newLine();


            bufferedWriter.flush();
        } catch (Exception e){
            logger.info(e.toString());
        } finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
            if (null != bufferedWriter){
                try {
                    bufferedWriter.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @RequestMapping("pdf")
    public void pdf(HttpServletResponse response){
        try {
            long start = System.currentTimeMillis();
            byte[] bytes = GeneratePDF.writeSimplePdf();
            response.addHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode("wodegeguaiguai".concat(".pdf"), "GBK"));
            response.setContentLength(bytes.length);
            OutputStream out = response.getOutputStream();
            /*InputStream in = new BufferedInputStream(new ByteArrayInputStream(bytes));
            byte[] b = new byte[in.available()];
            int len ;
            if ((len = in.read(b)) != -1){
                out.write(b,0,len);
            }*/
            out.write(bytes);
            long end = System.currentTimeMillis();
            long miao = end - start;
            logger.info("耗时：" + miao + "毫秒！！!");

        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
