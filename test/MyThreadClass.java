/**
 * Test
 */
class Test implements Runnable {
    public void run()
    {
        // moving thread2 to timed waiting state
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        System.out.println("4. thread state: " + MyThreadClass.myThread.getState());
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class MyThreadClass extends Thread{
    public static Thread myThread;
    public static Test obj;

    // initiating the thread and its in NEW state
    public void run () {

        try {
            Thread.sleep(1000); // Thread is in Timed Waiting state
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("3. thread state: " + Thread.currentThread().getState());

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        myThread = new MyThreadClass();
        System.out.println("1. thread state: " + myThread.getState()); //Still the thread is in NEW state

        myThread.start(); // now the thread is in RUNNABLE state
        System.out.println("2. thread state: " + myThread.getState());

        myThread.join();
        System.out.println("4. thread state: " + myThread.getState());
    }
}
