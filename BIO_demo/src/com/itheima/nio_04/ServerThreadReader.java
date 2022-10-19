package com.itheima.nio_04;

/*
@author YG
@create 2022/10/18   21:28
*/


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThreadReader implements Runnable {
    private Socket socket;

    //构造器，传入Socket对象，转变为线程对象
    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取socket输入流
            InputStream is = socket.getInputStream();
            //字节输入流-->字符输入流-->缓冲字符输入流
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = bf.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
