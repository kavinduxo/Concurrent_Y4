public class CurrentBankAccount implements BankAccount {

    private int accountNumber;
    private String customer;
    private int balance;
    private Statement statement;

    public CurrentBankAccount(int accountNumber, String customer, int balance) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.statement = new Statement(customer, accountNumber);
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getAccountHolder() {
        return this.customer;
    }

    @Override
    public synchronized void deposit(Transaction t) {
        int amountOfDeposit = t.getAmount();
        this.balance += amountOfDeposit;
        this.statement.addTransaction(t.getCID(), amountOfDeposit, this.balance);

        notifyAll();
    }

    @Override
    public synchronized void withdrawal(Transaction t) {
        int amountOfWithdrawal = t.getAmount();

        while (amountOfWithdrawal > this.balance) {
            try {
                System.out.println("[CurrentBankAccount]: Withdrawing " + amountOfWithdrawal + " from " + t.getCID() +
                        " was unsuccessful. Waiting until sufficient amount available...");
                wait();
            } catch (InterruptedException e) {
            }
        }

        this.balance -= amountOfWithdrawal;
        this.statement.addTransaction(t.getCID(), amountOfWithdrawal, this.balance);
        notifyAll();
    }

    @Override
    public boolean isOverdrawn() {
        return this.balance < 0;
    }

    @Override
    public void printStatement() {
        this.statement.print();
    }
}
