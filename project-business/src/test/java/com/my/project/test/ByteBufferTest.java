package com.my.project.test;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-21
 **/
public class ByteBufferTest {

    @Test
    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("capacity : " + byteBuffer.capacity());
        System.out.println("limit : " + byteBuffer.limit());
        System.out.println("position : " + byteBuffer.position());
        System.out.println("mark : " + byteBuffer.mark());
        System.out.println("---------------------------------------");
        byteBuffer.put("java".getBytes());

        System.out.println("capacity : " + byteBuffer.capacity());
        System.out.println("limit : " + byteBuffer.limit());
        System.out.println("position : " + byteBuffer.position());
        System.out.println("mark : " + byteBuffer.mark());
        System.out.println("---------------------------------------");

        byteBuffer.flip();

        System.out.println("capacity : " + byteBuffer.capacity());
        System.out.println("limit : " + byteBuffer.limit());
        System.out.println("position : " + byteBuffer.position());
        System.out.println("mark : " + byteBuffer.mark());
        System.out.println("---------------------------------------");

        byte[] bytes = new byte[byteBuffer.limit()];

        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        byteBuffer.clear();
        System.out.println("---------------------------------------");

        System.out.println("capacity : " + byteBuffer.capacity());
        System.out.println("limit : " + byteBuffer.limit());
        System.out.println("position : " + byteBuffer.position());
        System.out.println("mark : " + byteBuffer.mark());
        System.out.println("---------------------------------------");
    }
}
