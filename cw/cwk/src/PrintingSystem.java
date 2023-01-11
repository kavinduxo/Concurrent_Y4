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

public class PrintingSystem {

    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        ThreadGroup stdThreadGrp = new ThreadGroup("Students");
        ThreadGroup techThreadGrp = new ThreadGroup("Technicians");

        //Initializing a printer object
        Printer printer = new LaserPrinter(stdThreadGrp);

        Scanner mainScanner = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            String stdId = "std-" + (i + 1);
            System.out.print("Enter Student " + (i + 1) + " name: ");
            String stdName = mainScanner.next();

            Student student = new Student(stdId, stdName, stdThreadGrp, printer);
            studentList.add(i, student);
        }

        TonerTechnician tonerTechnician = new TonerTechnician("tech-1", "Toner Tech", techThreadGrp, printer);
        PaperTechnician paperTechnician = new PaperTechnician("tech-2", "Paper Tech", techThreadGrp, printer);

        try {
            studentList.get(0).start();
            studentList.get(1).start();
            studentList.get(2).start();
            studentList.get(3).start();
        } catch (Exception e) {
            System.out.println("MONITOR: " + e.getMessage());
        }

        tonerTechnician.start();
        paperTechnician.start();

        try {
            studentList.get(0).join();
            System.out.println(studentList.get(0).getStdName() + " Finished printing: 5 Documents, " + studentList.get(0).getTotalNoOfPapers() + " pages.");
            studentList.get(1).join();
            System.out.println(studentList.get(1).getStdName() + " Finished printing: 5 Documents, " + studentList.get(1).getTotalNoOfPapers() + " pages.");
            studentList.get(2).join();
            System.out.println(studentList.get(2).getStdName() + " Finished printing: 5 Documents, " + studentList.get(2).getTotalNoOfPapers() + " pages.");
            studentList.get(3).join();
            System.out.println(studentList.get(3).getStdName() + " Finished printing: 5 Documents, " + studentList.get(3).getTotalNoOfPapers() + " pages.");
            paperTechnician.join();
            System.out.println("Paper Technician(" + paperTechnician.getTechName() + ") Finished, packs of paper used: " + ((LaserPrinter) printer).getNoOfPaperPacksRefill());
            tonerTechnician.join();
            System.out.println("Toner Technician(" + tonerTechnician.getTechName() + ") Finished, cartridges replaced: " + ((LaserPrinter) printer).getNoOfTonerRefill());

            System.out.println("Printing process Completed!");
        } catch (InterruptedException e) {
            System.out.println("MONITOR: " + e.getMessage());
        }

    }
}