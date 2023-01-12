public class Grandmother extends Thread {

    private CurrentBankAccount currentBankAccount;
    private String name;

    public Grandmother(CurrentBankAccount account, String name, ThreadGroup group) {
        super(group, name);
        this.currentBankAccount = account;
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println("<------ | Starting Grandmother's Transactions from Bank Account | ------>");

        Transaction christmasGift = new Transaction(this.name, 40000);
        currentBankAccount.deposit(christmasGift);
        System.out.println("[Grandmother]: Deposited transaction amount of : " + christmasGift.toString());

        try {
            sleep( (int)(Math.random() * 100) ) ;
        }
        catch (InterruptedException e) { }

        Transaction birthdayGift = new Transaction(this.name, 30000);
        currentBankAccount.deposit(birthdayGift);
        System.out.println("[Grandmother]: Deposited transaction amount of : " + birthdayGift.toString());

        System.out.println("<------ | Terminating Grandmother's Transactions from Bank Account | ------>");
    }
}
