package com.znb.java.learn.nio;

import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 可以使用一个线程来处理所有通道
 * @author zhangnaibin@xiaomi.com
 * @time 2016-04-20 下午10:21
 */
public class SelectorTest {

    public void usage() {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            // 非阻塞模式channel注册到selector上
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);

            // 通过SelectionKey访问Channel和Selector
            Channel channel1 = selectionKey.channel();
            Selector selector1 = selectionKey.selector();

            // 将一个对象或者更多信息附着到SelectionKey
            Object obj = new Object();
            selectionKey.attach(obj);
            obj = selectionKey.attachment();

            // 向Selector注册Channel的时候附加对象
            channel.register(selector, selectionKey.interestOps(), obj);

            // 获取感兴趣事件,返回值标示就绪通道数量
            // 阻塞到至少有一个通道在你注册的事件上就绪了
            selector.select();
            // 和select()一样，除了最长会阻塞timeout毫秒(参数)
            selector.select(1000);
            // 不会阻塞，不管什么通道就绪都立刻返回
            selector.selectNow();

            // 处理就绪的事件
            Set<SelectionKey> okSet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = okSet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    //
                }
                if (key.isConnectable()) {

                }
            }


            // 唤醒阻塞在select（）的线程，没有阻塞线程会使下一个调用的线程立即醒来
            selector.wakeup();

            // 关闭selector，所有selectorKey失效，channle不会关闭
            selector.close();
        } catch (Exception e) {

        }

    }
}
