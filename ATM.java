package javatask;

import java.util.Scanner;

public class ATM {
    private double balance = 1000; // initial balance
    private int accountNumber = 123456789; // account number
    private int pin = 1234; // PIN
    private String[] transactions = new String[100]; // array to store transaction history
    private int transactionCount = 0; // count of transactions

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            String transaction = "Withdrawal of " + amount + ". New balance is " + balance;
            transactions[transactionCount] = transaction;
            transactionCount++;
            System.out.println("Withdrawal successful. Your balance is now " + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        String transaction = "Deposit of " + amount + ". New balance is " + balance;
        transactions[transactionCount] = transaction;
        transactionCount++;
        System.out.println("Deposit successful. Your balance is now " + balance);
    }

    public void checkBalance() {
        System.out.println("Your balance is " + balance);
    }

    public void transfer(int recipientAccountNumber, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            String transaction = "Transfer of " + amount + " to account " + recipientAccountNumber + ". New balance is " + balance;
            transactions[transactionCount] = transaction;
            transactionCount++;
            System.out.println("Transfer successful. Your balance is now " + balance);
        }
    }

    public void displayTransactions() {
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.println("Welcome to the ATM. Please enter your account number.");
        int account = scanner.nextInt();

        System.out.println("Please enter your PIN.");
        int pin = scanner.nextInt();
        boolean done = false;
while(!done) {
        if (account == atm.accountNumber && pin == atm.pin) {
            System.out.println("Login successful. What would you like to do?");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check balance");
            System.out.println("4. Transfer");
            System.out.println("5. View transaction history");
            System.out.println("6.Exit");

            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                    
                case 2:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    atm.checkBalance();
                    break;

                case 4:
                    System.out.println("Enter recipient account number:");
                    int recipientAccountNumber = scanner.nextInt();
                    System.out.println("Enter transfer amount:");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(recipientAccountNumber, transferAmount);
                    break;

                case 5:
                    atm.displayTransactions();
                    break;
                case 6:
                	done=true;
                	System.out.println("Thankyou for banking!!");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
                  
            }
        } 
    }
 
}    }
