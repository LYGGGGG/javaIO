package com.itheima.nio_04;

/*
@author YG
@create 2022/10/18   20:50
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步通信架构--线程池
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("=====服务端启动=====");
            //注册服务端Socket
            ServerSocket socket = new ServerSocket(9999);
            //创建线程池
            HandlerSocketServerPool pool = new HandlerSocketServerPool(2, 3, 2);
            while (true){
                //监听客户端请求
                Socket accept = socket.accept();
                //把socket封装成任务对象（该方法实现Runnable接口）
                Runnable task = new ServerThreadReader(accept);
                //把任务提交给线程池
                pool.excute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
