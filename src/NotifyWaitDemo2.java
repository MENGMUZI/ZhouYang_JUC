import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  10:05
 * @description: 四个线程操作初始值为零的一个变量，实现两个线程对该变量加1，两个线程对该变量减1，交替，来5轮
 */

class ShareData2{
    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try{
            //1.判断
            while (number != 0){
                condition.await();
            }
            //2.干活
            ++ number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3.通知
            condition.signalAll();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

    public void decrement(){
        lock.lock();
        try{
            //1.判断
            while (number == 0){
                condition.await();
            }
            //2.干活
            -- number;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3.通知
            condition.signalAll();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }

    }

}
public class NotifyWaitDemo2 {

    public static void main(String[] args) {

        ShareData2 shareData2 = new ShareData2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                //暂停一会儿线程
                try{ TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}
                shareData2.increment();
            }
        },"t1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                //暂停一会儿线程
                try{ TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}
                shareData2.decrement();
            }
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                //暂停一会儿线程
                try{ TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}
                shareData2.increment();
            }
        },"t3").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                //暂停一会儿线程
                try{ TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}
                shareData2.decrement();
            }
        },"t4").start();

    }

}
