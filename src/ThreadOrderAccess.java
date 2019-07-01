import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  17:42
 * @description: 多线程之间顺序调度，实现A->B->C
 *  三个线程启动，要求如下：
 *  *
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * 接着
 *  * AA打印5次，BB打印10次，CC打印15次
 *  * ......来10轮
 */

class ShareResource{
    private int number = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(int totalLoop){
        lock.lock();
        try{
            //1.判断
            while (number != 1){
                condition1.await();
            }
            //2.干活
            for (int i = 1; i <= 5 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoop:" + totalLoop);
            }
            //3.通知
            number = 2;
            condition2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }


    public void print10(int totalLoop){
        lock.lock();
        try{
            //1.判断
            while (number != 2){
                condition2.await();
            }
            //2.干活
            for (int i = 1; i <= 10 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoop:" + totalLoop);
            }
            //3.通知
            number = 3;
            condition3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(int totalLoop){
        lock.lock();
        try{
            //1.判断
            while (number != 3){
                condition3.await();
            }
            //2.干活
            for (int i = 1; i <= 15 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoop:" + totalLoop);
            }
            //3.通知
            number = 1;
            condition1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}


public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print5(i);
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print10(i);
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10 ; i++) {
                shareResource.print15(i);
            }
        },"CC").start();
    }

}
