import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author : mengmuzi
 * create at:  2019-07-01  20:51
 * @description: Callable接口获得多线程
 */
/**
 * 总结：
 * 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
 * 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。
 *
 * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
 *
 * 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
 * 就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
 * 然后会返回结果或者抛出异常。
 *
 * 只计算一次
 * get方法放到最后
 *
 * */

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        //暂停一会儿线程
        try{ TimeUnit.SECONDS.sleep(2);}catch(Exception e){e.printStackTrace();}
        System.out.println("*********call() *********");
        return 2019;
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AA").start();

        new Thread(futureTask,"BB").start();

        System.out.println(Thread.currentThread().getName()+"***********我是上课主线程");

        Integer result = futureTask.get();
        System.out.println("result:" + result);

    }

}
