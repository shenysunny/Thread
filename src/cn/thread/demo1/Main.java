package cn.thread.demo1;

public class Main {
    public static void main(String[] args) {
        //创建一个资源类
        Resource resource = new Resource();
        //启动生产线程
        new Thread(new Producter(resource)).start();
        //启动消费线程
        new Thread(new Custome(resource)).start();
    }
}
