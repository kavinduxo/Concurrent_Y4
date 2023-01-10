import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Thread{

    String stdId;
    String stdName;
    ThreadGroup threadGroup;
    Printer printer;

    public String getStdName() {
        return stdName;
    }

    public Student() {
    }

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
            System.out.println("User should enter Five document names with their page count.");
            System.out.print("Enter the Document Name: ");
            String docName = sc.next();
            docNametList.add(i, docName);

            System.out.print("Enter the Document Size: ");
            int docSize;
            try {
                docSize = Integer.parseInt(sc.next());
                if (docSize > ((ServicePrinter) printer).Full_Paper_Tray) {
                    throw new UserException("Maximum Number of Pages in Document should be less than 250.");
                }
                docSizeList.add(i, docSize);
            }catch (NumberFormatException | UserException e){
                System.out.println(e.getMessage());
                break;
            }
        }

        for (int i = 0; i < docNametList.size(); i++) {
            //Creating document instance
            Document document = new Document(this.stdId, docNametList.get(i), docSizeList.get(i));

            //Printing a document
            printer.printDocument(document);


            //Make thread sleep after printing a document
            int randomMilliSeconds = (int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000);
            try {
                sleep(randomMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
