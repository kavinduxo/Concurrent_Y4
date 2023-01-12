public class BankingSystem {

    private CurrentBankAccount studentBankAccount;

    private Student student;
    private Grandmother grandmother;
    private LoanCompany loanCompany;
    private University university;

    public BankingSystem() {

        ThreadGroup humans = new ThreadGroup("Humans");
        ThreadGroup organizations = new ThreadGroup("Organizations");

        this.studentBankAccount = new CurrentBankAccount(5504, "Isuru's Account", 0);

        this.student = new Student(studentBankAccount, studentBankAccount.getAccountHolder(), humans);
        this.grandmother = new Grandmother(studentBankAccount, "Isuru's grandmother", humans);

        this.loanCompany = new LoanCompany(studentBankAccount, "Loan Company", organizations);
        this.university = new University(studentBankAccount, "IIT", organizations);
    }

    public static void main (String[] args) {

        BankingSystem bankingSystem = new BankingSystem();

        bankingSystem.student.start();
        bankingSystem.grandmother.start();
        bankingSystem.loanCompany.start();
        bankingSystem.university.start();

        try {
            bankingSystem.student.join();
            bankingSystem.grandmother.join();
            bankingSystem.loanCompany.join();
            bankingSystem.university.join();
        } catch (InterruptedException e) { }

        bankingSystem.studentBankAccount.printStatement();
    }
}
