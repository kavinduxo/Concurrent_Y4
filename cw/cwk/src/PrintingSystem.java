import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintingSystem {

    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        ThreadGroup stdThreadGrp = new ThreadGroup("Students");
        ThreadGroup techThreadGrp = new ThreadGroup("Technicians");

        //Initializing a printer object
        Printer printer = new LaserPrinter(stdThreadGrp);

        Scanner mainScanner = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            String stdId = "std-" + i;
            System.out.print("Enter Student " + i + " name: ");
            String stdName = mainScanner.next();

            Student student = new Student(stdId, stdName, stdThreadGrp, printer);
            studentList.add(i, student);
        }

        TonerTechnician tonerTechnician = new TonerTechnician("tech-1", "Toner Tech", techThreadGrp, printer);
        PaperTechnician paperTechnician = new PaperTechnician("tech-2", "Paper Tech", techThreadGrp, printer);

        try{
            studentList.get(0).start();
            studentList.get(1).start();
            studentList.get(2).start();
            studentList.get(3).start();
        }catch (Exception e){
            e.printStackTrace();
        }

        tonerTechnician.start();
        paperTechnician.start();

        try {
            studentList.get(0).join();
            System.out.println(studentList.get(0).stdName + " work completed.");
            studentList.get(1).join();
            System.out.println(studentList.get(1).stdName + " work completed");
            studentList.get(2).join();
            System.out.println(studentList.get(2).stdName + " work completed");
            studentList.get(3).join();
            System.out.println(studentList.get(3).stdName + " work completed");
            paperTechnician.join();
            System.out.println(paperTechnician.techName + " work completed");
            tonerTechnician.join();
            System.out.println(tonerTechnician.techName + " work completed");

            System.out.println("Printing process Completed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}