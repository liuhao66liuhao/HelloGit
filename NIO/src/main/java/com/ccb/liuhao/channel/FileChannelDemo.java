package com.ccb.liuhao.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

public class FileChannelDemo {

    public static void main(String[] args) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/Users/haoliu/Downloads/a.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel fileChannel = ((FileInputStream) inputStream).getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        try {
            int i = fileChannel.read(byteBuffer);
            System.out.println(i);
            while(i != -1) {
                i = fileChannel.read(byteBuffer);
                System.out.println(i);
            }
            byteBuffer.flip();

            Charset charset = Charset.forName("UTF-8");
            CharBuffer charBuffer = charset.decode(byteBuffer);

            char[] result = Arrays.copyOf(charBuffer.array(),charBuffer.limit());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
