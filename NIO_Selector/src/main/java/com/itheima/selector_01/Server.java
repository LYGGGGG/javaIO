package com.itheima.selector_01;

/*
@author YG
@create 2022/10/24   18:08
*/

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO:服务端开发
 */
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("---------服务端启动---------");
        // 1、获取通道
        // nio的通信基于通道，首先在服务端提供通道，该通道专门负责客户端的连接请求
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 2、设置通道为非阻塞模式，因为是NIO
        socketChannel.configureBlocking(false);
        // 3、让客户端去连接服务端，绑定连接的端口
        socketChannel.bind(new InetSocketAddress(9999));
        // 4、获取选择器
        Selector selector = Selector.open();
        // 5、将通道注册到选择器，并设置监听事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6、使用Selector轮询通道事件
        while (selector.select() > 0) {
            // 7、就绪的事件以迭代器的方式给予
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 8、遍历事件
            while (iterator.hasNext()) {
                // 提取当前这个事件
                SelectionKey selectionKey = iterator.next();
                // 9、判断这个事件具体是什么
                if (selectionKey.isAcceptable()) {
                    // 10、获取当前接入的客户端通道
                    SocketChannel channel = socketChannel.accept();
                    // 11、切换成非阻塞模式
                    channel.configureBlocking(false);
                    // 12、将连接服务器的客户端通道注册到选择器
                    channel.register(selector, SelectionKey.OP_READ);
                }// 选择器下一轮读取事件，可能是刚刚连接的客户端的事件-->变成的读/写事件
                else if (selectionKey.isReadable()) {
                    // 13、获取当前的选择器上的读事件（反向获取）
                    SocketChannel socketChannel1 = (SocketChannel) selectionKey.channel();
                    // 14、读取事件，先创建缓冲
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    // while循环获取数据，因为一次不一定获取得全部
                    while ((len = socketChannel1.read(buffer)) > 0) {
                        // 读模式切换成写模式
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        // 循环装数据，需要清除之前的数据（归位）
                        buffer.clear();
                    }
                }
                // 处理完一个事件后，需要从迭代器中移除当前事件
                iterator.remove();
            }
        }
    }
}
