import java.util.concurrent.TimeUnit;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  16:21
 * @description:  8锁
 *
 * 1	标准访问,请问先打印短信还是email
 * 2	sendSMS()睡觉4秒钟,请问先打印短信还是email
 * 3	新增普通方法openPC,请问先打印短信还是openPhone
 * 4	有两部手机,请问先打印短信还是email
 * 5	两个静态同步方法,同一部手机,请问先打印短信还是email
 * 6	两个静态同步方法,2部手机,请问先打印短信还是email
 * 7	1个静态同步方法,1个普通同步方法,同一部手机,请问先打印短信还是email
 * 8	1个静态同步方法,1个普通同步方法,2部手机,请问先打印短信还是email
 *
 * * 笔记
 * 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
 * 其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 * 锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 *
 * 加个普通方法后发现和同步锁无关
 * 换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 * 锁定的是当前对象this,类锁,锁定的是整个class,是两个不同的对象
 *
 *
 * 都换成静态同步方法后，情况又变化
 * 所有的非静态同步方法用的都是同一把锁——实例对象本身，
 *
 * 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
 * 可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 * 所有的静态同步方法用的也是同一把锁——类对象本身，
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 */

class Phone{

    public static synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-------------sendSMS");
    }

    public  synchronized void sendEmail(){
        System.out.println("-------------sendEmail");
    }

    public void openPC()
    {
        System.out.println("--------------openPC");
    }


}
public class Lock_8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try
            {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try
            {
                //phone.sendEmail();
                //phone.openPC();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}
