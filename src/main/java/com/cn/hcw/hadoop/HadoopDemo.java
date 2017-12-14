package com.cn.hcw.hadoop;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/7/20 0020
 * Description:
 * Others:
 */
@Data
@NoArgsConstructor
@Slf4j
public class HadoopDemo {

    /*public static void main(String[] agrs) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("HDFS/application-hdfs.xml");
        FileSystem fileSystem = (FileSystem) context.getBean("fileSystem");
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/demo.xml"));
        FileInputStream inputStream = new FileInputStream(new File("D:\\hh.txt"));
        int i;
        while ((i = inputStream.read()) != -1){
            fsDataOutputStream.write(i);
        }
        inputStream.close();
        fsDataOutputStream.close();
        fileSystem.close();
    }*/

    private FileSystem fileSystem;

    /**
     * 按路径上传文件到hdfs
     * @param local
     * @param remote
     * @throws IOException
     */
    public void copyFile(String local, String remote) throws IOException {
        File file = new File(local);
        if (!file.exists()){
            log.info("需要上传的文件不存在:{}",local);
        }
        // 如果hdfs上存在要上传的文件，则会覆盖原来的文件
        if (fileSystem.exists(new Path(remote))) {
            log.info("hdfs上已经存在该文件:{}",remote);
        }
//        fileSystem.mkdirs(new Path(remote));
        //该方法会直接把local文件复制上传到remote，如果remote路劲不存在，会自动创建
        fileSystem.copyFromLocalFile(new Path(local), new Path(remote));
        System.out.println("copy from: " + local + " to " + remote);
        fileSystem.close();
    }
    /**
     * 按路径下载hdfs上的文件
     * @param remote
     * @param local
     * @throws IOException
     */
    public void download(String remote, String local) throws IOException {
        if (!fileSystem.exists(new Path(remote))) {
            log.info("hdfs上不存在该文件,{}",remote);
        }
        // 如果本地已经存在该文件，则会覆盖
        if (new File(local).exists()) {
            log.info("本地已经存在该文件：{}",local);
        }
        Path path = new Path(remote);
        fileSystem.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fileSystem.close();
    }
//    public static void main(String[] args) {
//        System.out.println(System.getenv("HADOOP_HOME"));
//    }

}
