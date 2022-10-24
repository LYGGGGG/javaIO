package com.itheima.selector_01;

/*
@author YG
@create 2022/10/24   23:46
*/


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        // 1、获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        // 2、切换成非阻塞模式
        socketChannel.configureBlocking(false);
        // 3、分配缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4、发送数据给服务器
        Scanner scanner = new Scanner(System.in);
        // 不断扫描用户输入
        while (true) {
            System.out.println("请说：");
            String msg = scanner.nextLine();
            buffer.put(("波妞：" + msg).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
    }
}
