import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : mengmuzi
 * create at:  2019-06-30  15:31
 * @description: 买票程序复习线程知识，3个售票员卖30张票
 *
 * 1.多线程编写魔板（上）
 *      1.1 线程  操作（方法） 资源类
 *      1.2 高内聚 低耦合
 *
 */
//资源类 类= 实例变量 + 实例方法
class Ticket{

    private int number;

    public Ticket(int number) {
        this.number = number;
    }

    private Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "\t 卖出第：" + number-- + "还剩下：" + number );
            }
        }finally{
            lock.unlock();
        }


    }
}
public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket(30);

        //Thread(Runnable target, String name)

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"A1").start();
        */

        for (int i = 1; i <= 3 ; i++) {
            new Thread(()->{
                for (int j = 1; j <= 30 ; j++) {
                    ticket.sale();
                }
            },String.valueOf(i)).start();
        }



    }

}
//1 class MyThread implements Runnable
//2 匿名内部类
//3 lambda Express
