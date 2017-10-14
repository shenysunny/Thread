package cn.thread.demo1;

/**
 * 消费类
 */
public class Custome implements  Runnable{
    //持有对方引用
    private  Resource resource;
    public Custome(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true){
            resource.out();
        }
    }
}
