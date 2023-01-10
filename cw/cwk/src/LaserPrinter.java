import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LaserPrinter implements ServicePrinter{

    private String printerId;
    private int paperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    private Lock lock;
    private Condition isLackOfResources;
    private Condition isAvailableResources;

    public LaserPrinter() {
        this.lock = new ReentrantLock();
        this.isAvailableResources = lock.newCondition();
        this.isLackOfResources = lock.newCondition();
    }
    public LaserPrinter(ThreadGroup threadGroup) {
        setThreadGroup(threadGroup);
        this.lock = new ReentrantLock();
        this.isAvailableResources = lock.newCondition();
        this.isLackOfResources = lock.newCondition();
    }

    private ThreadGroup threadGroup;


    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    public int getPaperLevel() {
        return paperLevel;
    }

    public void setPaperLevel(int paperLevel) {
        this.paperLevel = paperLevel;
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public void setTonerLevel(int tonerLevel) {
        this.tonerLevel = tonerLevel;
    }

    public int getDocumentsPrinted() {
        return documentsPrinted;
    }

    public void setDocumentsPrinted(int documentsPrinted) {
        this.documentsPrinted = documentsPrinted;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }




    /**
     * @param document
     */
    @Override
    public void printDocument(Document document) {
        lock.lock();
        try {
            while (tonerLevel < document.getNumberOfPages() || paperLevel < document.getNumberOfPages()) {
                isLackOfResources.await();
            }
            documentsPrinted++;
            isAvailableResources.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void replaceTonerCartridge() {
        lock.lock();
        try {
            while (tonerLevel < Minimum_Toner_Level){
                isAvailableResources.await();
            }
            tonerLevel = Full_Toner_Level;
            isLackOfResources.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void refillPaper() {
        int maxNoOfRemainingPapersToRefill = Full_Paper_Tray - SheetsPerPack;
        lock.lock();
        try {
            while (paperLevel > maxNoOfRemainingPapersToRefill) {
                isAvailableResources.await();
            }
            paperLevel += SheetsPerPack;
            isLackOfResources.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}
