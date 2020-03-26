package com.zf.jdk8stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: zf
 * @date: 2020-03-26 17:38
 * @description: 简单和复杂对象提取字段排序
 */
public class StreamSortedDemo {


    public static void main(String[] args) {
       List<Integer> list= Arrays.asList(1,2,3,23,12,11,9,3);

       System.out.println(list);
        System.out.println("------");
       List<Integer> retList= list.stream().sorted().collect(Collectors.toList());
        System.out.println(retList);
        System.out.println("------");
       List<Integer> descList= list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(descList);


        List<User> userList=Arrays.asList(
                new User("张三",12),
                new User("李思",22),
                new User("王五",11),
                new User("赵柳",19),
                new User("宋奇",39));

       List ascUserList= userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
       ascUserList.forEach(System.out::println);
        System.out.println("===========");
        List descUserList= userList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        System.out.println(descUserList.get(0));
        System.out.println("-----=======");
        descUserList.forEach(System.out::println);



    }


}

class User{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
