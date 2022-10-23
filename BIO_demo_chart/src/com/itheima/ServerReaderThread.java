package com.itheima;

/*
@author YG
@create 2022/10/19   20:12
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //从socket中获取当前客户端的输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("haha");
            String msg = reader.readLine();
            sendMsgToClient(msg);
            while ((msg = reader.readLine()) != null) {
                //服务端接收到客户端消息后，推送给当前所有的在线socket
                sendMsgToClient(msg);
            }
        } catch (Exception e) {
            System.out.println("socket下线");
            Server.ServerSocketsOnLine.remove(socket);
        }
    }

    /**
     * 把当前客户端发来的消息推送给全部在线的socket
     *
     * @param msg
     */
    public void sendMsgToClient(String msg) throws Exception {
        for (Socket s : Server.ServerSocketsOnLine) {
            PrintStream printStream = new PrintStream(s.getOutputStream());
            printStream.println("xxx");
            printStream.println(msg);
            printStream.flush();
        }
    }
}
