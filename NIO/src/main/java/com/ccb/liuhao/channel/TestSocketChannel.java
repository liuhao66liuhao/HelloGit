package com.ccb.liuhao.channel;

import java.nio.channels.SocketChannel;

public class TestSocketChannel {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocketChannelDemo.start();
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SocketChannelDemo socketChannelDemo = new SocketChannelDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SocketChannelDemo.send();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SocketChannelDemo.recieve();
            }
        }).start();
    }
}
