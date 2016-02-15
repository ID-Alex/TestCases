package io.sunyi.cases.nio.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sunyi on 15/8/26.
 */
public class Server {

    public static void main(String[] args) throws IOException {


        new Thread(new TimeServer(10010)).start();

    }

    public static class TimeServer implements Runnable {

        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        public TimeServer(int port) {
            try {
                selector = Selector.open();

                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                stop = false;
                System.out.println("Time Server started, port: " + port);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }


        public void run() {
            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    while (it.hasNext()) {
                        try {
                            SelectionKey key = it.next();
                            it.remove();
                            handlerInput(key);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void handlerInput(SelectionKey key) throws IOException {
            if (!key.isValid()) {
                return;
            }

            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);

                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");

                    System.out.println("接收报文：" + body);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? sdf.format(new Date()) : "ERROR ORDER";

                    doWrite(sc,currentTime);

                } else {
                    key.cancel();
                    sc.close();
                }
            }

        }

        private void doWrite(SocketChannel sc, String response) throws IOException {
            if (response == null || response.length() == 0) {
                return;
            }



            byte[] bytes = response.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            sc.write(buffer);
        }
    }

}
