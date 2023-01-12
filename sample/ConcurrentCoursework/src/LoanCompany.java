public class LoanCompany extends Thread {

    private CurrentBankAccount currentBankAccount;
    private String name;

    public LoanCompany(CurrentBankAccount bankAccount, String name, ThreadGroup group) {
        super(group, name);
        this.currentBankAccount = bankAccount;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("<------ | Starting LoanCompany's Transactions from Bank Account | ------>");

        for (int x = 0; x < 3; x++) {
            Transaction loanDepositAmount = new Transaction(this.name, 60000);
            currentBankAccount.deposit(loanDepositAmount);
            System.out.println("[LoanCompany]: Deposited transaction amount of : " + loanDepositAmount.toString());

            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }

        System.out.println("<------ | Terminating LoanCompany's Transactions from Bank Account | ------>");
    }
}
