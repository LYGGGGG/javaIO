package com.itheima;

/*
@author YG
@create 2022/10/24   10:58
*/

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
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
