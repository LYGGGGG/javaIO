package com.itheima.nio_file;

/*
@author YG
@create 2022/10/19   8:37
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：服务端开发，可以实现接收客户端的任意类型文件，并保存到服务端磁盘
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.建立服务端socket
            ServerSocket serverSocket = new ServerSocket(9999);
            //2.死循环，接收客户端的socket连接
            while (true){
                //3.监听客户端的socket请求
                Socket socket = serverSocket.accept();
                //4.传入socket，启动线程
                ServerReaderThread serverReaderThread = new ServerReaderThread(socket);
                serverReaderThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
