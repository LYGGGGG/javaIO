package com.itheima;

/*
@author YG
@create 2022/10/19   19:35
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * BIO端口转发
 * <p>
 * 服务端实现的需求:【多人在线聊天室】
 * 1、注册端口
 * 2、接收客户端的socket连接，交给一个独立的线程来处理
 * 3、把当前连接的客户端socket存入到一个所谓的  在线socket集合 中保存
 * 4、接收客户端的消息，然后推送给当前所有在线的socket接收。
 */
public class Server {
    //定义一个静态集合
    public static List<Socket> ServerSocketsOnLine = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true){
                Socket socket = serverSocket.accept();
                //把登录的客户端socket存入一个在线集合中
                ServerSocketsOnLine.add(socket);
                //为当前登录成功的socket分配一个独立线程来处理，与之通信
                new ServerReaderThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
