//package io.sunyi.cases.aio;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.Future;
//
///**
// * Created by sunyi on 15/9/1.
// */
//public class TimeServer {
//
//    public static void main(String[] args) {
//
//        new Thread(new TimeServerWork(10011)).start();
//
//    }
//
//    public static class TimeServerWork implements Runnable {
//
//        private AsynchronousServerSocketChannel assc;
//        private CountDownLatch latch;
//
//        public TimeServerWork(int port) {
//            try {
//                assc = AsynchronousServerSocketChannel.open();
//                assc.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
//                System.out.println("Started......");
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(-1);
//            }
//        }
//
//        @Override
//        public void run() {
//
//            latch = new CountDownLatch(1);
//
//            assc.accept(this, new CompletionHandler<AsynchronousSocketChannel, TimeServerWork>() {
//                @Override
//                public void completed(AsynchronousSocketChannel result, TimeServerWork attachment) {
//                    attachment.assc.accept(attachment, this);
//
//                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    result.read(buffer, buffer, new ReadCompletionHandler(result));
//                }
//
//                @Override
//                public void failed(Throwable exc, TimeServerWork attachment) {
//                    exc.printStackTrace();
//                }
//            });
//
//            try {
//                System.out.println(latch.getCount());
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
//
//        private AsynchronousSocketChannel asc;
//
//        public ReadCompletionHandler(AsynchronousSocketChannel result) {
//            this.asc = result;
//        }
//
//        @Override
//        public void completed(Integer result, ByteBuffer attachment) {
//            try {
//                attachment.flip();
//                byte[] bytes = new byte[attachment.remaining()];
//                attachment.get(bytes);
//                String body = new String(bytes, "UTF-8");
//                System.out.println("ORDER IS " + body);
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? sdf.format(new Date()) : "ERROR ORDER";
//
//                ByteBuffer wbytes = ByteBuffer.allocate(currentTime.getBytes("UTF-8").length);
//                wbytes.put(currentTime.getBytes("UTF-8"));
//                wbytes.flip();
//
//                Future<Integer> write = asc.write(wbytes);
//                System.out.println("write: " + write.get());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.exit(-1);
//            }
//        }
//
//        @Override
//        public void failed(Throwable exc, ByteBuffer attachment) {
//            try {
//                asc.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
