package com.zf;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @auther: zf
 * @date: 2020-03-28 17:09
 * @description: stream的步骤
 * 1创建Stream
 * 2中间操作
 * 3终止操作（终端操作）
 * <p>
 * <p>
 * <p>
 * 中间操作
 */
public class StreamApi2 {
    @Test
    public void test2() {
        BigDecimal x=new BigDecimal( "1000");
        BigDecimal y=new BigDecimal("0");
        BigDecimal xian= x.multiply(new BigDecimal("5"));
        for (int i = 0; i <1000 ; i++) {
            BigDecimal sy= computer( x);
            x= x.subtract(sy);
            y=y.add(sy);
            if(y.compareTo(new BigDecimal("200"))>=0){
                System.out.println("第"+i+"天后收益回本"+y);
            }
        }


    }

    public BigDecimal computer(BigDecimal x){

        BigDecimal sy= x.multiply(new BigDecimal("0.001"));
        return sy;
    }

}
