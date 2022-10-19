package com.itheima.nio_file;

/*
@author YG
@create 2022/10/19   11:41
*/

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //1.从socket中获取输入流
            InputStream inputStream = socket.getInputStream();
            //2.包装
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            //3.创建缓冲存储输入的数据
            byte[] buffer = new byte[1024];
            int len;
            //4.获取数据后缀
            String suffix = dataInputStream.readUTF();
            System.out.println("服务端已经成功接收到了文件类型：" + suffix);
            //5.把获取的数据写到服务器，因此创建输出流
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\server\\"+ UUID.randomUUID().toString() + suffix);
            while ((len = inputStream.read(buffer)) > 0) {
                //6.写入服务器
                fileOutputStream.write(buffer,0,len);
            }
            fileOutputStream.close();
            System.out.println("服务端接收文件保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
