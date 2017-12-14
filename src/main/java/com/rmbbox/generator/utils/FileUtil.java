package com.rmbbox.generator.utils;

import java.io.*;

/**
 * Created by lxl on 15/9/23.
 */
public class FileUtil {

    /**
     * 创建文件
     *
     * @param fileName
     * @return boolean
     * @author MASK
     */
    public static boolean createFile(File fileName) throws Exception {
        boolean flag = false;
        try {
            if (!fileName.exists()) {
                fileName.createNewFile();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 读取文件内容
     *
     * @param fileName 文件路径
     * @return String
     * @author MASK
     */
    public static String readTxtFile(File fileName) {
        String result = "";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                result = result + read + "\r\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 读入文件内容
     *
     * @param content 内容
     * @param fileName 文件路径
     * @return boolean
     * @author MASK
     */
    public static boolean writeTxtFile(String content, File fileName)
            throws Exception {
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("UTF-8"));
            o.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                mm.close();
            }
        }
        System.out.println("生成["+fileName.getName()+"]完成!");
        return flag;
    }

    public static boolean createFolder(String path){
        boolean flag = false;
        try {
            File file = new File(path);
            file.mkdirs();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean createFolders(String ... paths){
        boolean flag = false;
        for (String path : paths) {

            try {
                File file = new File(path);
                file.mkdirs();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
                return flag;
            }
        }
        return flag;
    }

}
