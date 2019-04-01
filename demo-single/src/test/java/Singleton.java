public class Singleton {

    private Singleton(){}

    private static   class  SinglentonInten{
       private static Singleton singleton=new Singleton();
    }

    public static Singleton getInstance(){
         return  SinglentonInten.singleton;
    }
}
