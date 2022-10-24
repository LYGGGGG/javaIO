package com.itheima;

/*
@author YG
@create 2022/10/24   10:58
*/

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    @Test
    public void copy() throws Exception {
        //源文件、目标文件
        File fileIn = new File("C:\\Users\\Administrator\\Desktop\\data\\test.jpg");
        File fileOut = new File("C:\\Users\\Administrator\\Desktop\\copy\\test01.jpg");
        //字节输入流、输出流
        FileInputStream inputStream = new FileInputStream(fileIn);
        FileOutputStream outputStream = new FileOutputStream(fileOut);
        //两个通道
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        //分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            //清空缓冲区，才能继续写入
            buffer.clear();
            //读取数据
            int flag = inChannel.read(buffer);
            if (flag == -1){
                break;
            }
            //把缓冲区的模式切换成可读模式
            buffer.flip();
            //将buffer中的数据写入到outChannel中
            outChannel.write(buffer);
        }
        inChannel.close();
        outChannel.close();
        System.out.println("复制完成");
    }

    @Test
    public void read() throws IOException {
        // 1、字节输入流通向目标文件
        FileInputStream inputStream = new FileInputStream("data01.txt");
        // 2、得到字节输入流对应的通道Channel
        FileChannel channel = inputStream.getChannel();
        // 3、分配缓冲区buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);
        buffer.flip();
        String msg = new String(buffer.array(),0,buffer.remaining());
        System.out.println(msg);
    }


    @Test
    public void write() throws IOException {
        // 1、字节输出流通向目标文件
        FileOutputStream outputStream = new FileOutputStream("data01.txt");
        // 2、得到字节输出流对应的通道Channel，通过文件输出流获得
        FileChannel channel = outputStream.getChannel();
        // 3、分配缓冲区buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String name = "hello, world!";
        buffer.put(name.getBytes());
        // 4、缓冲区的写入模式-->切换成读出模式
        buffer.flip();
        // 5、将缓冲区的数据写入文件通道
        channel.write(buffer);

        channel.close();
        System.out.println("写数据到文件中!");
    }
}
