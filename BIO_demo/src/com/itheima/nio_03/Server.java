package com.itheima.nio_03;

/*
@author YG
@create 2022/10/18   12:58
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：一个服务端可以接收多个客户端的消息
 * 思路：服务端每接收到一个客户端socket请求，都交给一个独立的线程来处理客户端的数据交互需求
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("=====服务端启动=====");
        try {
            //1.通过ServerSocket注册服务端端口
            ServerSocket serverSocket = new ServerSocket(9999);
            //2.定义一个死循环，不断地接收客户端的socket连接请求
            while (true){
                Socket socket = serverSocket.accept();
                //3.创建一个独立的线程，来处理新的客户端socket连接请求
                new ServerThreadReader(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

