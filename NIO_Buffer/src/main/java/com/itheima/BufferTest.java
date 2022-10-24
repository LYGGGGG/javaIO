package com.itheima;

/*
@author YG
@create 2022/10/23   21:47
*/

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest {
    @Test
    public void test01() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        String name = "itheima";
        buffer.put(name.getBytes());

        System.out.println("======================");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        buffer.flip();
        System.out.println("======================");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println("======================");
        System.out.println((char) buffer.get());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }

    @Test
    public void test02() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("===========================");

        String name = "itheima";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("===========================");

        buffer.clear();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("===========================");
        System.out.println((char) buffer.get());
    }

    @Test
    public void test03() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("----------------");

        String name = "itheima";
        buffer.put(name.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("----------------");

        buffer.flip();

        byte[] b1 = new byte[2];
        buffer.get(b1);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("----------------");

        buffer.mark();

        byte[] b2 = new byte[3];

        buffer.get(b2);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("----------------");

        buffer.reset();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println("----------------");

        if (buffer.hasRemaining()){
            int remaining = buffer.remaining();
            System.out.println(remaining);
        }
    }
}
