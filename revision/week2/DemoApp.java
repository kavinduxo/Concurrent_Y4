package week2;

public class DemoApp {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new MyThread("T1");
        t1.start();

        Thread t2 = new MyThread("T2");
        t2.start();

        Thread t3 = new MyThread();
        t3.start();

        Runnable r1 = new RunnableThread("Runnable1");
        Thread t4 = new Thread(r1, ((RunnableThread) r1).getName());

        t4.start();
    }
}
