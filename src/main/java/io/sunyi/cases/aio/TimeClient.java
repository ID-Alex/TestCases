//package io.sunyi.cases.aio;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
///**
// * Created by sunyi on 15/9/2.
// */
//public class TimeClient {
//
//    public static void main(String args[]) {
//        new Thread(new TimeClientWork(10011)).start();
//    }
//
//    public static class TimeClientWork implements Runnable {
//
//        private int port;
//        private CountDownLatch latch;
//        private AsynchronousSocketChannel asc;
//
//
//
//
//        public TimeClientWork(int port) {
//            try {
//
//                this.port = port;
//
//                asc = AsynchronousSocketChannel.open();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(-1);
//            }
//        }
//
//
//        @Override
//        public void run() {
//            try {
//                latch = new CountDownLatch(1);
//                asc.connect(new InetSocketAddress(InetAddress.getLocalHost(), port), this, new CompletionHandler<Void, TimeClientWork>() {
//                    @Override
//                    public void completed(Void result, TimeClientWork attachment) {
//
//                        try {
//                            String reqBody = "QUERY TIME ORDER";
//                            byte[] bytes =  reqBody.getBytes("UTF-8");
//                            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
//                            buffer = buffer.put(bytes);
//                            buffer.flip();
//                            attachment.asc.write(buffer, attachment, new CompletionHandler<Integer, TimeClientWork>() {
//
//                                @Override
//                                public void completed(Integer result, TimeClientWork attachment) {
//
//                                    try {
//                                        ByteBuffer wb = ByteBuffer.allocate(1024);
//
//                                        Future<Integer> read = attachment.asc.read(wb);
//
//                                        System.out.println("read: " + read.get());
//
//                                        wb.flip();
//
//                                        System.out.println(new String(wb.array(),"UTF-8") + "------");
//
//
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    try {
//                                        attachment.asc.close();
//                                        attachment.latch.countDown();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
//
//                                @Override
//                                public void failed(Throwable exc, TimeClientWork attachment) {
//                                    try {
//                                        attachment.asc.close();
//                                        attachment.latch.countDown();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                            try {
//                                attachment.asc.close();
//                                attachment.latch.countDown();
//                            } catch (IOException e1) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, TimeClientWork attachment) {
//                        exc.printStackTrace();
//                        try {
//                            attachment.asc.close();
//                            attachment.latch.countDown();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                latch.countDown();
//            }
//
//
//            try {
//
//                latch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
