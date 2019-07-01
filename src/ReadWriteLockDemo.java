import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  20:36
 * @description:  一个线程写入,100个线程读取
 */

class MyQueue{

    private Object obj;

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void writeObject(Object obj){
        rwLock.writeLock().lock();
        try{
            this.obj = obj;
            System.out.println(Thread.currentThread().getName() + "\t" + obj);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwLock.writeLock().unlock();
        }

    }

    public void readObject(){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            rwLock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();

        new Thread(()->{
            myQueue.writeObject("Hello World!!!!");
        },"writeThread").start();

        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(1);}catch(Exception e){e.printStackTrace();}

        for (int i = 1; i <= 100 ; i++) {
            new Thread(()->{
                myQueue.readObject();
            },String.valueOf(i) + "\t" + "readThread").start();
        }

    }

}
