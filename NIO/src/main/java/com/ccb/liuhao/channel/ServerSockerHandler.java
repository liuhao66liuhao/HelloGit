package com.ccb.liuhao.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ServerSockerHandler implements Runnable {

    private SocketChannel socketChannel;

    public ServerSockerHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        try {
            while (true) {
                int i = socketChannel.read(byteBuffer);
                if (i == -1 || i == 0) {
                    continue;
                }
                byteBuffer.flip();
                Charset charset = Charset.forName("UTF-8");
                CharBuffer charBuffer = charset.decode(byteBuffer);
                char[] chars = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
                System.out.println(chars);

//                byteBuffer.clear();
//                String message = "收到";
//                byteBuffer.put(message.getBytes(Charset.forName("UTF-8")));
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    socketChannel.write(byteBuffer);
                }
                byteBuffer.clear();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
