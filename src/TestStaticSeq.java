/**
 * @author : mengmuzi
 * create at:  2019-07-01  17:04
 * @description: 类加载顺序
 */

class Father{
    public Father(){
        System.out.println("11111");

    }

    {
        System.out.println("22222");
    }

    static {
        System.out.println("33333");
    }
}

class Son extends Father{
    public Son(){
        super();
        System.out.println("44444");
    }

    {
        System.out.println("55555");
    }

    static {
        System.out.println("66666");
    }
}
public class TestStaticSeq {

    public static void main(String[] args) {
        new Son(); //-->Son.class -->Father.class --> Father Instance --> Son Instance
        System.out.println("-------------------");

        new Son();
        System.out.println("-------------------");

        new Father();
    }


}
