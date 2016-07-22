package org.readwritetxt;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	long start=System.currentTimeMillis();
       
        for (int i = 0; i < 100000; i++) {
        	appendMethodB("d:\\test.txt","001016052201757|654666666999911258|2016-05-22 14:20:49|2016-05-22 15:04:39|888|企业账户|大军|中国银行|6217900100001611111|北京|北京|622301198410259178|15117978901|3.2|0.03|成功|");
		}
        long end=System.currentTimeMillis();
        System.out.println("耗时"+ String.valueOf(end-start)+"毫秒");
        
    }
    
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
