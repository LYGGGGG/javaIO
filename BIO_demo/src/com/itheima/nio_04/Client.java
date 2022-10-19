package com.itheima.nio_04;

/*
@author YG
@create 2022/10/18   21:47
*/

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("=====客户端启动=====");
        try {
            //连接服务端
            Socket socket = new Socket("127.0.0.1", 9999);
            //从socket管道中获取字节输出流
            OutputStream os = socket.getOutputStream();
            //字节输出流包装成打印流
            PrintStream printStream = new PrintStream(os);
            Scanner scanner = new Scanner(System.in);
            String  msg;
            while ((msg = scanner.nextLine())!=null){
                printStream.println(msg);
                printStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
