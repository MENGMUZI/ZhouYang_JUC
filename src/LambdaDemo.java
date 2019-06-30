/**
 * @author : mengmuzi
 * create at:  2019-06-30  16:32
 * @description: Lambda 表达式的案例
 *  1. 拷贝中括号 + 写死右箭头 + 落地大括号
 *  2. 一个接口中有且仅有一个方法，才能使用Lambda Express
 *  3. @FunctionalInterface
 *  4. defult 默认方法实现
 *  5. 静态方法实现
 */

interface Foo{

    //public void sayHello();

    //public void sayBye();

    public int add(int x , int y);

    default int div(int x, int y){

        return x/y;
    }

    public static int sub(int x, int y){

        return x - y;
    }
}

public class LambdaDemo {

    public static void main(String[] args) {
        /*
        Foo foo = new Foo(){

            @Override
            public void sayHello() {
                System.out.println("Hell0 world!");
            }
        };

        foo.sayHello();
        */

        /*
        Foo foo = ()->{
            System.out.println("Hell0 world!");
        };
        foo.sayHello();
        */

        Foo foo = (int x, int y) -> {return x + y;};

        int result = foo.add(3,5);

        System.out.println("add result:" + result);

        System.out.println("div result:" + foo.div(6,3));

        System.out.println("sub result:" + Foo.sub(7,1));
    }

}
