package com.coderdream;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * NIO读写文件工具类
 */
public class NIOFileUtil {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public NIOFileUtil(String file) throws IOException {
        super();
        this.file = file;
    }

    /**
     * NIO读取文件
     *
     * @param allocate
     * @throws IOException
     */
    public void read(int allocate) throws IOException {

        RandomAccessFile access = new RandomAccessFile(this.file, "r");

        //FileInputStream inputStream = new FileInputStream(this.file);
        FileChannel channel = access.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);

        CharBuffer charBuffer = CharBuffer.allocate(allocate);
        Charset charset = Charset.forName("GBK");
        CharsetDecoder decoder = charset.newDecoder();
        int length = channel.read(byteBuffer);
        while (length != -1) {
            byteBuffer.flip();
            decoder.decode(byteBuffer, charBuffer, true);
            charBuffer.flip();
            System.out.println(charBuffer.toString());
            // 清空缓存
            byteBuffer.clear();
            charBuffer.clear();
            // 再次读取文本内容
            length = channel.read(byteBuffer);
        }
        channel.close();
        if (access != null) {
            access.close();
        }
    }

    /**
     * NIO写文件
     *
     * @param context
     * @param allocate
     * @param chartName
     * @throws IOException
     */
    public void write(String context, int allocate, String chartName) throws IOException {
        // FileOutputStream outputStream = new FileOutputStream(this.file); //文件内容覆盖模式 --不推荐
        FileOutputStream outputStream = new FileOutputStream(this.file, true); //文件内容追加模式--推荐
        FileChannel channel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        byteBuffer.put(context.getBytes(chartName));
        byteBuffer.flip();//读取模式转换为写入模式
        channel.write(byteBuffer);
        channel.close();
        if (outputStream != null) {
            outputStream.close();
        }
    }

    /**
     * nio事实现文件拷贝
     *
     * @param source
     * @param target
     * @param allocate
     * @throws IOException
     */
    public static void nioCopy(String source, String target, int allocate) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(allocate);
        FileInputStream inputStream = new FileInputStream(source);
        FileChannel inChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream(target);
        FileChannel outChannel = outputStream.getChannel();

        int length = inChannel.read(byteBuffer);
        while (length != -1) {
            byteBuffer.flip();//读取模式转换写入模式
            outChannel.write(byteBuffer);
            byteBuffer.clear(); //清空缓存，等待下次写入
            // 再次读取文本内容
            length = inChannel.read(byteBuffer);
        }
        outputStream.close();
        outChannel.close();
        inputStream.close();
        inChannel.close();
    }

    //IO方法实现文件k拷贝
    private static void traditionalCopy(String sourcePath, String destPath) throws Exception {
        File source = new File(sourcePath);
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws Exception {
        /*long start = System.currentTimeMillis();
        traditionalCopy("D:\\常用软件\\JDK1.8\\jdk-8u181-linux-x64.tar.gz", "D:\\常用软件\\JDK1.8\\IO.tar.gz");
        long end = System.currentTimeMillis();
        System.out.println("用时为：" + (end-start));*/

        long start = System.currentTimeMillis();
        nioCopy("D:\\常用软件\\JDK1.8\\jdk-8u181-linux-x64.tar.gz", "D:\\常用软件\\JDK1.8\\NIO.tar.gz", 1024);
        long end = System.currentTimeMillis();
        System.out.println("用时为：" + (end - start));
    }

}

