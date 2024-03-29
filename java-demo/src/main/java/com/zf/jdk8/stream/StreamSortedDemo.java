package com.zf.jdk8.stream;

import java.util.*;
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
        System.out.println("======收集器的======");
        Set<Integer> retList2= list.stream().sorted().collect(Collectors.toSet());
        System.out.println(retList2);


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
        System.out.println("--------数据器成一个map");
        Map<String,Integer> descUserMap= userList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toMap(user->user.getName(),user->user.getAge()));
        System.out.println(descUserMap);

        //转换成其DTO的示例
        List list2=userList.stream().map(e-> new UserDTO(e.getName(),e.getAge()+"")).collect(Collectors.toList());

        System.out.println(list2);





    }


}

class UserDTO{
    private String name;
    private String age;

    public UserDTO(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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
