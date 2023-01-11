/**
 * ********************************************************************
 * Author:  Kavindu Gunathilake
 * UoWID:   W1761405
 * Date:      23/01/11
 * ***********************************************************************
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Thread {

    private String stdId;
    private String stdName;
    private ThreadGroup threadGroup;
    private Printer printer;
    private int totalNoOfPapers;

    public Student(String stdId, String stdName, ThreadGroup threadGroup, Printer printer) {
        this.stdName = stdName;
        this.threadGroup = threadGroup;
        this.stdId = stdId;
        this.printer = printer;
    }

    Scanner sc = new Scanner(System.in);
    List<String> docNametList = new ArrayList<>();
    List<Integer> docSizeList = new ArrayList<>();

    /**
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            String docName = "Document Bundle-" + (i + 1);
            docNametList.add(i, docName);

            int docSize = (int) Math.floor(Math.random() * (25 - 1 + 1) + 1);
            docSizeList.add(i, docSize);

//            try {
//                docSize = Integer.parseInt(sc.next());
//                if (docSize > ((ServicePrinter) printer).Full_Paper_Tray) {
//                    throw new UserException("Maximum Number of Pages in Document should be less than 250.");
//                }
//                docSizeList.add(i, docSize);
//            }catch (NumberFormatException | UserException e){
//                System.out.println(e.getMessage());
//                break;
//            }
        }

        for (int i = 0; i < docNametList.size(); i++) {
            //Creating document instance
            Document document = new Document(this.stdId, docNametList.get(i), docSizeList.get(i));
            this.totalNoOfPapers += docSizeList.get(i);

            //Printing a document
            printer.printDocument(document);
            System.out.println("STUDENT: Printer Status " + printer.toString());

            //Make thread sleep after printing a document
            int randomMilliSeconds = (int) Math.floor(Math.random() * (5000 - 1000 + 1) + 1000);
            try {
                sleep(randomMilliSeconds);
            } catch (InterruptedException e) {
                System.out.println("STUDENT: " + e.getMessage());
            }

        }
    }

    public int getTotalNoOfPapers() {
        return totalNoOfPapers;
    }

    public String getStdName() {
        return stdName;
    }
}
