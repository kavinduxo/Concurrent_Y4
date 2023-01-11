import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LaserPrinter implements ServicePrinter {

    private String printerId;
    private int paperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    private Lock lock;
    //private Condition condition;
    //private Condition isAvailableResources;
    private ThreadGroup threadGroup;

    public LaserPrinter() {
        this.lock = new ReentrantLock();
        //this.isAvailableResources = lock.newCondition();
        //this.condition = lock.newCondition();
    }

    public LaserPrinter(ThreadGroup threadGroup) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Printer ID: ");
        String printer = scanner.next();

        setPrinterId(printer);
        setThreadGroup(threadGroup);
        setPaperLevel(Full_Paper_Tray);
        setTonerLevel(Full_Toner_Level);
        setDocumentsPrinted(0);

        //this.lock = new ReentrantLock(true);
        //this.isAvailableResources = lock.newCondition();
        //this.condition = lock.newCondition();
    }

    /**
     * @param document
     */
    @Override
    public synchronized void printDocument(Document document) {
        //lock.lock();
        //try {
            while (tonerLevel < document.getNumberOfPages() || paperLevel < document.getNumberOfPages()) {
                if (paperLevel < document.getNumberOfPages()) {
                    System.out.println("Insufficient Papers. Waiting until refill");
                } else if (tonerLevel < document.getNumberOfPages()) {
                    System.out.println("Insufficient Toner level. Waiting until cartridge is replace");
                } else {
                    System.out.println("Ehmath wenwada????");
                }

                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //condition.await(5000, TimeUnit.MILLISECONDS);
            }
            if (paperLevel >= document.getNumberOfPages() && tonerLevel >= document.getNumberOfPages()) {
                documentsPrinted++;
                paperLevel -= document.getNumberOfPages();
                tonerLevel -= document.getNumberOfPages();
                System.out.println("PRINTER: Printing Completed " + document);
            }
            notifyAll();
            //condition.signalAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //lock.unlock();
//        }

    }

    @Override
    public synchronized void replaceTonerCartridge() {
        //lock.lock();
        //try {
            while (tonerLevel > Minimum_Toner_Level) {
                if(isPrinterUsageFinish()) {
                    System.out.println("no need to replace Toner");
                    break;
                }
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //condition.await(5000, TimeUnit.MILLISECONDS);
            }
            if (tonerLevel < Minimum_Toner_Level){
                tonerLevel = Full_Toner_Level;
                System.out.println("TONER TECH: Replace Toner.");
            }
            notifyAll();
            //condition.signalAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //lock.unlock();
//        }
    }

    @Override
    public synchronized void refillPaper() {
//        lock.lock();
//        try {
            int maxNoOfRemainingPapersToRefill = Full_Paper_Tray - SheetsPerPack;
            while (paperLevel > maxNoOfRemainingPapersToRefill) {
                if(isPrinterUsageFinish()) {
                    System.out.println("no need to refill papers");
                    break;
                }
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //condition.await(5000, TimeUnit.MILLISECONDS);
            }
            if (paperLevel <= maxNoOfRemainingPapersToRefill){
                paperLevel += SheetsPerPack;
                System.out.println("PAPER TECH: Refilled Papers.");
            }
            notifyAll();
            //condition.signalAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //lock.unlock();
//        }
    }

    @Override
    public String toString() {
        String printerState = "[ PrinterID: " + this.printerId + ", Paper Level: " + this.paperLevel + ", Toner Level: " + this.tonerLevel + ", Documents Printed: " + this.documentsPrinted + " ]";
        return printerState;
    }

    private boolean isPrinterUsageFinish() {
        return this.threadGroup.activeCount() ==0;
    }

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

}
