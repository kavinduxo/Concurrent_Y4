/**
 * ********************************************************************
 * Author:  Kavindu Gunathilake
 * UoWID:   W1761405
 * Date:      23/01/11
 * ***********************************************************************
 */

import java.util.Scanner;

public class LaserPrinter implements ServicePrinter {

    private String printerId;
    private int paperLevel;
    private int tonerLevel;
    private int documentsPrinted;
    private int noOfPaperPacksRefill;
    private int noOfTonerRefill;
    private ThreadGroup threadGroup;

    public LaserPrinter(ThreadGroup threadGroup) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Printer ID: ");
        String printer = scanner.next();

        setPrinterId(printer);
        setThreadGroup(threadGroup);
        setPaperLevel(Full_Paper_Tray);
        setTonerLevel(Full_Toner_Level);
        setDocumentsPrinted(0);
    }

    /**
     * @param document
     */
    @Override
    public synchronized void printDocument(Document document) {
        while (tonerLevel < document.getNumberOfPages() || paperLevel < document.getNumberOfPages()) {
            try {
                if (paperLevel < document.getNumberOfPages()) {
                    throw new UserException("Insufficient Papers. Waiting for refill by the Paper Technician.");
                } else if (tonerLevel < document.getNumberOfPages()) {
                    throw new UserException("Insufficient Toner level. Waiting for replace the cartridge by Toner Technician.");
                }
                wait(5000);
            } catch (InterruptedException | UserException e) {
                System.out.println("PRINTER: " + e.getMessage());
            }
        }
        if (paperLevel >= document.getNumberOfPages() && tonerLevel >= document.getNumberOfPages()) {
            documentsPrinted++;
            paperLevel -= document.getNumberOfPages();
            tonerLevel -= document.getNumberOfPages();
            System.out.println("PRINTER: Printing Completed " + document);
        }
        notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {
        while (tonerLevel > Minimum_Toner_Level) {
            if (isPrinterUsageFinish()) {
                System.out.println("PRINTER: No need to replace Toner. No Student is waiting to Print.");
                break;
            }
            try {
                wait(5000);
            } catch (InterruptedException e) {
                System.out.println("PRINTER: " + e.getMessage());
            }
        }
        if (tonerLevel < Minimum_Toner_Level && noOfTonerRefill < 3) {
            tonerLevel = Full_Toner_Level;
            this.noOfTonerRefill++;
            System.out.println("PRINTER: Replace Toner.");
        } else if (noOfTonerRefill >= 3) {
            try {
                throw new UserException("Maximum Toners can replace is 3.");
            } catch (UserException e) {
                System.out.println("PRINTER: " + e.getMessage());
            }
        }
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {
        int maxNoOfRemainingPapersToRefill = Full_Paper_Tray - SheetsPerPack;
        while (paperLevel > maxNoOfRemainingPapersToRefill) {
            if (isPrinterUsageFinish()) {
                System.out.println("PRINTER: No need to refill Papers. No Student is waiting to Print.");
                break;
            }
            try {
                wait(5000);
            } catch (InterruptedException e) {
                System.out.println("PRINTER: " + e.getMessage());
            }
        }
        if (paperLevel <= maxNoOfRemainingPapersToRefill && noOfPaperPacksRefill < 3) {
            paperLevel += SheetsPerPack;
            this.noOfPaperPacksRefill++;
            System.out.println("PRINTER: Refilled Papers.");
        } else if (noOfPaperPacksRefill >= 3) {
            try {
                throw new UserException("Maximum Paper packs can replace is 3.");
            } catch (UserException e) {
                System.out.println("PRINTER: " + e.getMessage());
            }
        }
        notifyAll();
    }

    @Override
    public String toString() {
        String printerState = "[ PrinterID: " + this.printerId + ", Paper Level: " + this.paperLevel + ", Toner Level: " + this.tonerLevel + ", Documents Printed: " + this.documentsPrinted + " ]";
        return printerState;
    }

    private boolean isPrinterUsageFinish() {
        return this.threadGroup.activeCount() == 0;
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

    public int getNoOfPaperPacksRefill() {
        return noOfPaperPacksRefill;
    }

    public void setNoOfPaperPacksRefill(int noOfPaperPacksRefill) {
        this.noOfPaperPacksRefill = noOfPaperPacksRefill;
    }

    public int getNoOfTonerRefill() {
        return noOfTonerRefill;
    }

    public void setNoOfTonerRefill(int noOfTonerRefill) {
        this.noOfTonerRefill = noOfTonerRefill;
    }

}
