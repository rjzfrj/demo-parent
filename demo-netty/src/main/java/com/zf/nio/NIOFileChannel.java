package com.zf.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @auther: zf
 * @date: 2020-04-07 11:52
 * @description: 利用FileChannel 给文件中写入内容
 */
public class NIOFileChannel {
    public static void main(String[] args) throws Exception {
//        write();

//        read();

//        readWrite();

        copyPrc();
    }

    private static void write() throws IOException {
        String str="hello xx nio";
//        FileOutputStream fileOutputStream=new FileOutputStream("d:\\file.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/lucy/file.txt");
        FileChannel fileChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    private static void read() throws IOException {
        File file=new File("d:\\file.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel fileChannel=fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate((int)file.length());
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
    private static void readWrite() throws IOException {
        File file=new File("d:\\file.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel fileChannel=fileInputStream.getChannel();
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\file1.txt");
        FileChannel fileChannel2= fileOutputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
        byteBuffer.clear();
        while (true) {
            int read = fileChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            System.out.println(new String(byteBuffer.array(),"utf-8"));
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }


        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copyPrc() throws IOException{
        FileInputStream fileInputStream=new FileInputStream("d:\\file.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\file_back.txt");
       FileChannel sourceCh= fileInputStream.getChannel();
       FileChannel destCh=fileOutputStream.getChannel();
       destCh.transferFrom(sourceCh,0,sourceCh.size());
       sourceCh.close();
       destCh.close();
       fileInputStream.close();
       fileOutputStream.close();

    }
}
