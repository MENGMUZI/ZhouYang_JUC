import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : mengmuzi
 * create at:  2019-07-04  23:08
 * @description: 集合类不安全
 */

/**
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 */
public class NotSafeDemo {

    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();


        //写时复制
        List<String> list = new CopyOnWriteArrayList<>();//解决集合类线程不安全

//        list = Arrays.asList("a","b","c");

//        list.forEach(System.out::println);


        //多线程向list中添加元素
        for (int i = 1; i <= 10 ; i++) {//java.util.ConcurrentModificationException
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
