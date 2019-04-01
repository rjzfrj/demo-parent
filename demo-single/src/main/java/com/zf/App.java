package com.zf;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BuildOrderNumSingle  buildOrderNumSingle= BuildOrderNumSingle.getInstance();
        System.out.println( "Hello World!" );

        Singleton singleton=Singleton.getInstance();
//        for (int i=0;i<100;i++) {
//            new Thread(new ThreadMy(singleton),"Thread-"+i).start();
//        }


        for (int i=0;i<200;i++) {
            new Thread(new ThreadMy2(buildOrderNumSingle),"Thread-"+i).start();
        }
    }
}
