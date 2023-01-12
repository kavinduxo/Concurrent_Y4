import java.util.*;

public class Student extends Thread {

    private CurrentBankAccount currentBankAccount;
    private String name;

    public Student(CurrentBankAccount account, String name, ThreadGroup group) {
        super(group, name);
        this.currentBankAccount = account;
        this.name = name;
    }

    private void randomizeTransactions(List<Transaction> transactionList) {
        Set indexes = new HashSet();
        Random random = new Random();
        int iteratedIndexes = 0;

        while (iteratedIndexes < transactionList.size()) {
            int index = random.nextInt(6);
            if (indexes.add(index)) {
                if (index > 2) {
                    currentBankAccount.withdrawal(transactionList.get(index));
                    System.out.println("[Student]: Withdrawal transaction amount of : " + transactionList.get(index));
                } else {
                    currentBankAccount.deposit(transactionList.get(index));
                    System.out.println("[Student]: Deposited transaction amount of : " + transactionList.get(index));
                }

                try {
                    sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {

                }
                iteratedIndexes++;

            }
        }
    }

        @Override
        public void run () {
            System.out.println("<------ | Starting Student's Transactions from Bank Account | ------>");

            Transaction pocketMoney = new Transaction(this.name, 5000);
            Transaction winLottery = new Transaction(this.name, 50000);

            Transaction accommodationPayments = new Transaction(this.name, 10000);
            Transaction buyCloths = new Transaction(this.name, 5000);
            Transaction buySamsungPhone = new Transaction(this.name, 30000);
            Transaction routerBill = new Transaction(this.name, 2000);

            List<Transaction> transactionsList = new ArrayList<>();
            transactionsList.add(pocketMoney);
            transactionsList.add(winLottery);
            transactionsList.add(accommodationPayments);
            transactionsList.add(buyCloths);
            transactionsList.add(buySamsungPhone);
            transactionsList.add(routerBill);
            randomizeTransactions(transactionsList);

            currentBankAccount.printStatement();

            System.out.println("<------ | Terminating Student's Transactions from Bank Account | ------>");
        }
    }
