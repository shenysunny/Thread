package cn.thread.demo2;

/**
 * 生产类
 */
public class Producter implements Runnable{
    //持有对方引用
    private Resource resource;
    public Producter(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true){
            resource.set("苹果7");
        }
    }
}
