import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : mengmuzi
 * create at:  2019-07-03  23:00
 * @description: 第四种获得多线程的方式，线程池
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        testThreadPool1();
    }

    public static void testThreadPool1(){

        //ExecutorService executorService1 = Executors.newFixedThreadPool(5);//一池5线程
        //ExecutorService executorService1 = Executors.newSingleThreadExecutor();//一池1线程
        ExecutorService executorService1 = Executors.newCachedThreadPool();//一池 N 线程

        Future<Integer> result = null;

        try{
            for (int i = 1; i <= 15 ; i++) {
                result = executorService1.submit(()->{
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                    return new Random().nextInt(10);
                });
                System.out.println("*************result: " + result.get());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            executorService1.shutdown();
        }





    }
}
