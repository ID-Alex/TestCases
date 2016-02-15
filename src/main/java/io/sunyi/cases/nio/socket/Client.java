package io.sunyi.cases.nio.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sunyi on 15/8/27.
 */
public class Client {

    public static void main(String[] args) {
        new Thread(new TimeClient()).start();
    }

    public static class TimeClient implements Runnable {

        private SocketChannel sc;
        private Selector selector;
        private boolean stop;

        public TimeClient() {
            try {
                selector = Selector.open();
                sc = SocketChannel.open();
                sc.configureBlocking(false);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }

        public void run() {

            try {
                doConn();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();

                        System.out.println("key.isValid():" + key.isValid());
                        System.out.println("key.isConnectable():" + key.isConnectable());
                        System.out.println("key.isAcceptable():" + key.isAcceptable());
                        System.out.println("key.isWritable():" + key.isWritable());
                        System.out.println("key.isReadable():" + key.isReadable());
                        System.out.println("-----------------------------------");


                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-1);
                }
            }

        }

        private void doConn() throws IOException {
            boolean connect = sc.connect(new InetSocketAddress(InetAddress.getLocalHost(), 10010));
            if (connect) {
                sc.register(selector, SelectionKey.OP_READ);
                doWriter(sc);
            } else {
                sc.register(selector, SelectionKey.OP_CONNECT);
            }


        }

        private void handleInput(SelectionKey key) throws IOException {
            if (!key.isValid()) {
                return;
            }

            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);


                    doWriter(sc);
                } else {
                    System.exit(-1);
                }
            }

            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] byteAraay = new byte[buffer.remaining()];
                    buffer.get(byteAraay);
                    System.out.println(new String(byteAraay, "UTF-8"));
                } else {
                    key.cancel();
                    sc.close();
                }

            }
        }


        private void doWriter(SocketChannel sc) throws IOException {

            byte[] bytes = "QUERY TIME ORDER".getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            sc.write(buffer);

            if (!buffer.hasRemaining()) {
                System.out.println("SEND ORDER SUCC");
            }


        }
    }
}
