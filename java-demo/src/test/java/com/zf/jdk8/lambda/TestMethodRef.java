package com.zf.jdk8.lambda;

import com.zf.jdk8.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @auther: zf
 * @date: 2020-03-29 10:16
 * @description: 方法引用：若Lambda体中的内容有方法已经实现了，我们使用"方法引用" 即
 *        （可以理解方法引用是Lambda 表达式的另外一种表现形式）
 *  一    主要有三种语法格式：
 *      对象::实例方法
 *      类::静态方法名
 *      类::实例方法名
 *
 *      1 (关键要看函数式接口中的方法  void accept(T t) 的参数列表和返回值类型要与Lambda方法体中的参数列表和返回类型一致)
 *  二。构造器引用
 *
 *  格式 ：ClassName::new
 *  注意:需要调用的构造器的参数列表要与函数式接口中的抽象方法参数列表保证一致
 *
 */
public class TestMethodRef {

    /**
     * 构造器引用
     */
    @Test
    public void test4() {
    Function<Integer, Employee> function=(x)->new Employee(1);
    Function<Integer, Employee> function2=Employee::new;
    }




    /**
     *  类::实例方法名
     */
    @Test
    public void test3(){
        BiPredicate<String,String> comparator=(x, y)->x.equals(y);
        System.out.println(comparator.test("12","11"));
        System.out.println("还可以下面的方式");
        BiPredicate<String,String> comparator2=String::equals;
        System.out.println(comparator2.test("1","1"));

        Function<String,String> function=(x)->x.replace("a","A");
        System.out.println(function.apply("hello abc"));
        Function<String,String> function3=(x)->x.toUpperCase();
        Function<String,String> function2=String::toUpperCase;
        System.out.println(function2.apply("hello abc"));

    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test2(){
        Comparator<Integer> comparator=(x, y)->Integer.compare(x,y);
        System.out.println(comparator.compare(1,2));
        System.out.println("还可以下面的方式");
        Comparator<Integer> comparator2=Integer::compare;
        System.out.println(comparator.compare(1,2));
    }

    /**
     *  对象::实例方法
     */
    @Test
    public void test1(){
        PrintStream ps=System.out;
        Consumer<String> consumer=(x)->ps.println(x);
        consumer.accept("hello");
//        可以直接写成下面的形式
        Consumer<String> consumer1=System.out::println;
        consumer1.accept("hello2");
    }







}
