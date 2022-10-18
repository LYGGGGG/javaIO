package com.itheima.nio_02;

/*
@author YG
@create 2022/10/18   12:58
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
    public static void main(String[] args) throws IOException {
        System.out.println("=====客户端启动=====");
        // （1）创建一个Socket的通信管道，请求与服务端的端口连接。
        Socket socket = new Socket("127.0.0.1",9999);
        // （2）从Socket通信管道中得到一个字节输出流。
        OutputStream outputStream = socket.getOutputStream();
        // （3）把字节流包装成一个打印流
        PrintStream printStream = new PrintStream(outputStream);

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请说：");
            String next = scanner.next();
            printStream.println(next);
            printStream.flush();
        }

    }
}
