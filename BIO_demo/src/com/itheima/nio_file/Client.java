package com.itheima.nio_file;

/*
@author YG
@create 2022/10/19   8:37
*/

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * 目标：实现客户端上传  任意类型  的文件数据给服务端保存起来。
 */
public class Client {
    public static void main(String[] args) {
        try {
            //1.建立socket连接
            Socket socket = new Socket("127.0.0.1", 9999);
            //2.将socket的字节输出流转为data输出流（任意格式）
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //3.输出文件的格式
            dataOutputStream.writeUTF(".jpg");
            //4.创建文件输入流，读取本地文件
            InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\data\\test.jpg");
            //5.创建缓冲
            byte[] buffer = new byte[1024];
            int len;
            //6.把输入流读取到的数据，写入缓冲流
            while ((len = inputStream.read(buffer)) > 0) {
                //7.将缓冲内的数据，写入数据输出流
                dataOutputStream.write(buffer, 0, len);
            }
            //8.刷新
            dataOutputStream.flush();
            //9.通知服务端，客户端的数据发送完毕，否则服务端在客户端发送完后，仍在等待
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
