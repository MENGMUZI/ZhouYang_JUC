import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengmuzi
 * create at:  2019-07-03  22:35
 * @description: Semaphore 6部车抢3个车位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位

        for (int i = 1; i <= 6 ; i++) {//模拟6辆汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t *****抢占了一个车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(8));
                    System.out.println(Thread.currentThread().getName() + "\t *****离开了一个车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();

        }

    }
}
