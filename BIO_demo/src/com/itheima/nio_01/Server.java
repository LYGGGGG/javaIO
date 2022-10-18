package com.itheima.nio_01;

/*
@author YG
@create 2022/10/18   12:58
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：Server 接收 Client 发送的一条数据
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.通过ServerSocket注册服务端端口
            ServerSocket serverSocket = new ServerSocket(9999);
            //2.监听客户端Socket连接请求
            Socket accept = serverSocket.accept();
            //3.从socket中获取字节输入流
            InputStream inputStream = accept.getInputStream();
            //4.把字节输入流包装成字符输入流，再包装成缓冲字符输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            if ((msg = bufferedReader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

