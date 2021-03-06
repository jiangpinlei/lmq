package com.liuj.lmq.server;

import com.liuj.lmq.client.IMessageListener;
import com.liuj.lmq.core.Message;
import com.liuj.lmq.utils.AssertUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by cdliujian1 on 2016/11/17.
 */
public class ServerManager {

    /**
     * server topic，机器队列
     */
    public final static ConcurrentMap<String, LinkedBlockingDeque<Message>> ALL_MESSAGES = new ConcurrentHashMap<String, LinkedBlockingDeque<Message>>();


    /**
     * 订阅主题
     * ALL_MESSAGES同时生成对应的列表
     *
     * @param topic
     */
    public synchronized static LinkedBlockingDeque checkMessageTopic(String topic) {
        AssertUtil.notNull(topic);
        LinkedBlockingDeque<Message> messages = ALL_MESSAGES.get(topic);
        if (messages == null) {
            messages = new LinkedBlockingDeque<Message>();
            ALL_MESSAGES.putIfAbsent(topic, messages);
        }
        return messages;
    }

}
