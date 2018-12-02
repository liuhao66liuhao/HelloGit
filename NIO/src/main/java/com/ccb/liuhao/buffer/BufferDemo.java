package com.ccb.liuhao.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {

    private static void decode(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(bytes);
        byteBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(byteBuffer);

        char[] result = Arrays.copyOf(charBuffer.array(),charBuffer.limit());
        System.out.println(result);

    }

    private static byte[] encode(String str) {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.put(str);
        charBuffer.flip();

        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(charBuffer);

        byte[] bytes = Arrays.copyOf(byteBuffer.array(),byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));
        return bytes;
    }

    public static void main(String[] args) {
        decode(encode("哈哈"));
    }
}
