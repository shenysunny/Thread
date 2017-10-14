package cn.thread.demo1;

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

    //生产者
    public synchronized void set(String name) {
        //判断资源库中是否有货
        if (hasGoods) {
            try {
                //等待
                this.wait();
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
        //通知消费线程
        notify();

    }

    //消费者
    public synchronized void out() {
        //判断资源库中是否有货
        if (!hasGoods) {
            try {
                //等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("消费线程名称： " + Thread.currentThread().getName());
        System.out.println("消费商品名称： " + this.name);
        System.out.println("-----------------------------------------------------------------------");
        //把商品状态标记为true
        hasGoods = false;
        //通知生产线程
        notify();

    }
}
