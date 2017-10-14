package cn.thread.demo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
public class Resource {
    //商品名称
    private String name;
    //商品编号
    private int number = 1;
    //是否有商品
    private boolean hasGoods = false;
    //引入lock锁对象
    private final Lock lock = new ReentrantLock();
    //创建lock锁对象的监控对象(生产者)
    private final Condition pro_conn = lock.newCondition();
    //创建lock锁对象的监控对象(消费者)
    private final Condition cus_conn = lock.newCondition();

    //生产者
    public void set(String name) {
        //获取 lock 锁
        lock.lock();
        try {
            //判断资源库中是否有货
            while (hasGoods) {
                try {
                    //显示锁对象lock的等待方法
                    pro_conn.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name = name + "__" + number;
            number++;
            System.out.print("生产线程名称： " + Thread.currentThread().getName());
            System.out.println("生产商品名称： " + this.name);
            //把商品状态标记为true
            hasGoods = true;
            //唤醒消费线程
            cus_conn.signalAll();
        } finally {
            //释放lock
            lock.unlock();
        }

    }

    //消费者
    public void out() {
        //获取 lock 锁
        lock.lock();
        try {
            //判断资源库中是否有货
            while (!hasGoods) {
                try {
                    //显示锁对象lock的等待方法
                    cus_conn.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("消费线程名称： " + Thread.currentThread().getName());
            System.out.println("消费商品名称： " + this.name);
            System.out.println("-----------------------------------------------------------------------");
            //把商品状态标记为true
            hasGoods = false;
            //唤醒生产线程
            pro_conn.signalAll();
        } finally {
            //释放lock
            lock.unlock();
        }

    }
}
