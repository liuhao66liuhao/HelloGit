package com.ccb.liuhao.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

public class SocketChannelDemo {

    private static final String IP = "127.0.0.1";

    private static final int PORT = 7890;

    private static SocketChannel socketChannel;

    public SocketChannelDemo() {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(IP, PORT));
            socketChannel.configureBlocking(false);

            while (!socketChannel.finishConnect()) {
                Thread.sleep(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void send() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            while (true) {
                String message = null;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                message = bufferedReader.readLine();
                byteBuffer.put(message.getBytes(Charset.forName("UTF-8")));
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println("客户端发送：" + Arrays.toString(Arrays.copyOf(byteBuffer.array(), byteBuffer.limit())));
                    socketChannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void recieve() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            while (true) {
                int j = socketChannel.read(byteBuffer);
                if (j == -1 || j ==0) {
                    continue;
                }
                byteBuffer.flip();
                CharBuffer charBuffer = Charset.forName("UTF-8").decode(byteBuffer);
                char[] chars = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
                System.out.println(chars);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
