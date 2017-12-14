package com.cn.hcw.rabbit;

/**
 * Created by lenovo on 2017/3/31 0031.
 */
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.lang.SerializationUtils;

public class Sender extends BaseConnector {
    public Sender(String queueName) throws IOException {
        super(queueName);
    }

    public void sendMessage(Serializable object) throws IOException {
        //往队列中发出一条消息
        channel.basicPublish("",queueName, null, SerializationUtils.serialize(object));
    }
}
