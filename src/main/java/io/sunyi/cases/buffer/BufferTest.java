package io.sunyi.cases.buffer;

import java.nio.ByteBuffer;

/**
 * Created by sunyi on 15/8/28.
 */
public class BufferTest {
    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        buffer.putChar('A');
        System.out.println(buffer.remaining());
        buffer.flip();
        System.out.println(buffer.remaining());
        char c = buffer.getChar();
        System.out.println(c);
        System.out.println(buffer.remaining());
        buffer.clear();
        System.out.println(buffer.remaining());


    }
}
