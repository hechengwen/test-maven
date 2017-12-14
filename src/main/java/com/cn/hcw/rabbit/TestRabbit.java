package com.cn.hcw.rabbit;

/**
 * Created by lenovo on 2017/3/31 0031.
 */
public class TestRabbit {
    public static void main(String[] args) throws Exception{
        Receiver receiver = new Receiver("testQueue");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
        Sender sender = new Sender("testQueue");
        for (int i = 0; i < 5; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setChannel("test=" + i);
            messageInfo.setContent("msg=" + i);
            sender.sendMessage(messageInfo);
        }
    }
}
