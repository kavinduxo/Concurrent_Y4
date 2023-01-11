/**
 * ********************************************************************
 * Author:  Kavindu Gunathilake
 * UoWID:   W1761405
 * Date:      23/01/11
 * ***********************************************************************
 */

public class PaperTechnician extends Thread {

    String techId;
    String techName;
    ThreadGroup threadGroup;
    Printer printer;

    public String getTechName() {
        return techName;
    }

    public PaperTechnician(String techId, String techName, ThreadGroup threadGroup, Printer printer) {
        this.techId = techId;
        this.techName = techName;
        this.threadGroup = threadGroup;
        this.printer = printer;
        //Set paper technician priority to max priority since papers can ran-out very often.
        setPriority(MAX_PRIORITY);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            System.out.println(getPriority());
            //Refilling the papers of printer
            ((LaserPrinter) printer).refillPaper();
            System.out.println("PAPER TECH: Printer Status " + printer.toString());

            //Make thread sleep after refill papers of the printer
            int randomMilliSeconds = (int) Math.floor(Math.random() * (5000 - 1000 + 1) + 1000);

            try {
                sleep(randomMilliSeconds);
            } catch (InterruptedException e) {
                System.out.println("PAPER TECH: " + e.getMessage());
            }
        }
    }
}
