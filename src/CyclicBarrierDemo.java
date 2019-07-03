import java.util.concurrent.CyclicBarrier;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  22:15
 * @description: CyclicBarrier
 *
 * CyclicBarrier 的字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是,
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有
 * 被屏障拦截的线程才会继续干活。线程进入屏障通过CyclicBarrier的await()方法。
 */
public class CyclicBarrierDemo {

    private static final int NUMBER = 7;

    public static void main(String[] args) {
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER , ()->{
            System.out.println("*********召唤神龙");
        });

        for (int i = 1; i <= NUMBER; i++) {
            int tempInt = i;
            new Thread(()->{
                try{
                    System.out.println(Thread.currentThread().getName() + "\t" + "收集到第：" + tempInt + "\t 龙珠");
                    cyclicBarrier.await();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }

}
