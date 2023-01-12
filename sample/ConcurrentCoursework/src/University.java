public class University extends Thread {

    private CurrentBankAccount currentBankAccount;
    private String name;

    public University (CurrentBankAccount account, String name, ThreadGroup group) {
        super(group, name);
        this.currentBankAccount = account;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("<------ | Starting University's Transactions from Bank Account | ------>");

        for (int x = 0; x < 3; x++) {
            Transaction courseFeeAmount = new Transaction(this.name, 50000);
            currentBankAccount.withdrawal(courseFeeAmount);
            System.out.println("[University]: Withdraw transaction amount of : " + courseFeeAmount.toString());

            try {
                sleep( (int)(Math.random() * 100) ) ;
            }
            catch (InterruptedException e) { }
        }

        System.out.println("<------ | Terminating University's Transactions from Bank Account | ------>");
    }
}
