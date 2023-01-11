public class TonerTechnician extends Thread {
    String techId;
    String techName;
    ThreadGroup threadGroup;
    Printer printer;
    public String getTechName() {
        return techName;
    }

    public TonerTechnician(String techId, String techName, ThreadGroup threadGroup, Printer printer) {
        this.techId = techId;
        this.techName = techName;
        this.threadGroup = threadGroup;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            //Refilling the papers of printer
            ((LaserPrinter) printer).replaceTonerCartridge();
            System.out.println("TONER TECH: Printer Status " + printer.toString());

            //Make thread sleep after replace the Toner cartridge of the printer
            int randomMilliSeconds = (int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000);

            try {
                sleep(randomMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
