package com.zf.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @auther: zf
 * @date: 2020-04-07 11:52
 * @description:
 */
public class NIOFileChannel {
    public static void main(String[] args) throws Exception {
        String str="hello xx nio";
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\file.txt");
        FileChannel fileChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}
