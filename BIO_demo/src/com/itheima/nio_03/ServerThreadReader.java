package com.itheima.nio_03;

/*
@author YG
@create 2022/10/18   17:03
*/

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThreadReader extends Thread {
    private Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //从socket对象中得到字节输入流
            InputStream inputStream = socket.getInputStream();
            //包装
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
