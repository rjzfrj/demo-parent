package com.zf.jdk8.lambda;

import com.zf.jdk8.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @auther: zf
 * @date: 2020-03-28 19:26
 * @description: java8 内置四大核心函数式接口 （扩展的也提供了很多）
 *   Consumer<T>  : 消费型接口
 *     void accept(T t);
 *   Supplier<T> :提供型接口
 *     T get();
 *   Function<T,R> : 函数型接口
 *     R apply(T t);
 *   Predicate<T> :断言型接口
 *     boolean test(T t);
 *
 */
public class TestLimbda<T> {

    /**
     *  断言型接口
     */
    @Test
    public void predicateTest(){
      List<String> list=Arrays.asList("abc","ccddb","132412b");
      List<String> s=filter(list,(x)->x.length()>3);
        System.out.println(s);
        System.out.println("上面是字符串长度大于3---------包含c的");
        List<String> s2=filter(list,(x)->x.indexOf("c")!=-1);
        System.out.println(s2);
    }

    /**
     * 满足 条件的过滤
     * @param strList
     * @param predicate
     * @return
     */
    public List<String> filter(List<String> strList, Predicate<String> predicate ){
        List<String> list=new ArrayList<>();
        for (String str: strList) {
            if(predicate.test(str)){
            list.add(str);
           }
        }
      return  list ;
    }


    /**
     * 函数型接口
     */
    @Test
    public void functionTest(){
       String ret= square(10,(x)->{ return x+"的平方是："+x*x;});
        System.out.println(ret);
        System.out.println("--------");
        String yy= square(10,(x)->{ return x+"的立方方是："+x*x*x;});
        System.out.println(yy);
    }
    public String square(Integer x,Function<Integer,String> fun){
       return fun.apply(x);
    }

    /**
     * 提供型接口的使用 招聘10个人
     */
    @Test
    public void testSupplier(){
     List<String> employe=   zhaopin(10,()-> new Employee(new Random(47).nextInt(26)+"a",(int)Math.random()*100,(double)Math.random()*5000).toString());
       employe.forEach(System.out::println);
        System.out.println("=======不同的实现=======");
        List<String> xx=   zhaopin(10,()-> System.currentTimeMillis()+"");
        xx.forEach(System.out::println);
    }
    public List<String> zhaopin(Integer num, Supplier<String> emp){
        List<String> list=new ArrayList<>();
        for (int i = 0; i <num ; i++) {
            list.add(emp.get());
        }
       return list;
    }


    /**
     * 消费型接口的使用
     */
    @Test
    public void test(){
        consumerMsg("今天晚上开会",(x)->{
            System.out.println("广播一条消息："+x);
        });
    }

    public void consumerMsg(String str,Consumer<String> consumer){
        consumer.accept(str);
    }


}
