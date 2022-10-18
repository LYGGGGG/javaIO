package com.itheima.nio_02;

/*
@author YG
@create 2022/10/18   12:58
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：Server 可以反复接收消息，Client 可以反复发送消息
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("=====服务端启动=====");
            //1.通过ServerSocket注册服务端端口
            ServerSocket serverSocket = new ServerSocket(9999);
            //2.监听客户端Socket连接请求
            Socket accept = serverSocket.accept();
            //3.从socket中获取字节输入流
            InputStream inputStream = accept.getInputStream();
            //4.把字节输入流包装成字符输入流，再包装成缓冲字符输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

