

/**
 * @author : mengmuzi
 * create at:  2019-07-01  00:54
 * @description: 两个线程操作初始值为零的一个变量，实现一个线程对该变量加1，一个线程对该变量减1，交替，来5轮
 *
 *  1 多线程编写套路------上
 *     1.1	线程		操作(实例方法)		资源类
 *     1.2  高内聚  低耦合
 *  2 多线程编写套路------下
 *     2.1 判断
 *     2.2 干活
 *     2.3 通知
 *
 *  3 多线程中的判断用while，避免虚假唤醒
 */

class ShareData{

    private int number = 0;


    public synchronized void increment() throws InterruptedException {
        //1.判断
        //if(number != 0){  虚假唤醒
        while(number != 0){
            this.wait();
        }
        //2.干活
        ++ number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3.通知
        this.notifyAll();
    }


    public synchronized void decrement() throws InterruptedException {
        //1.判断
        //if(number == 0){
        while(number == 0){
            this.wait();
        }
        //2.干活
        -- number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //3.通知
        this.notifyAll();
    }
}


public class NotifyWaitDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();


        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();


        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }

}
