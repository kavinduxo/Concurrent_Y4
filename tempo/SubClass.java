package tempo;
public class SubClass extends SuperClass implements Runnable {
    public void run() {
        System.out.println("Thread is running....");
    }
    public SubClass (String newConstr){
         System.out.println(newConstr);
    }

    public static void main(String[] args) {
        SubClass rr = new SubClass("new Constructor");
        Thread tt = new Thread(rr);
        tt.start();
        
        rr.display();
        
    }

    
}