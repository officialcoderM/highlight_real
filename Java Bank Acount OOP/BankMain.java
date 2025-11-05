 //Author: Mahdi Bandali
//September 18, 2025
//Professor Gao
//COS-210-010
//Project 1
//This is my main method which implements my BankAccount, CheckingAccount and SavingsAccount
//Thanks for giving us this project because it was easy to understand as I use a bank regularly
//Although it was easy to understand coding it was not so easy and in one or two areas I took help from my tutor
//from tutor.com and annotated where with a comment on that line. It was the do while loop where I learnt that
//implementation from the tutor and applied it to this code.
//I also created a menu() function as well as others so i would not need to repeat 5-7 lines of code throughout the main method
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class BankMain {
    public static int menu () {
        Scanner userSelection = new Scanner(System.in);
        int choice = 0;
        while (choice < 1 || choice > 6){
            try {
                System.out.println();
                System.out.println("1> Get account summary");
                System.out.println("2> Create a new account");
                System.out.println("3> Make deposit");
                System.out.println("4> Withdraw");
                System.out.println("5> Process check");
                System.out.println("6> Exit");
                System.out.println("Please input your choice (1-6):");
                choice = userSelection.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Error: Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e ){
                System.out.println("Error: Please enter a number between 1 and 6.");
                userSelection.next();
                choice = 0;
} }
        return choice;
    }
    public static int accountCreator() {
        System.out.println("You want to create a new\n");
        System.out.println("1> Checking Account\n");
        System.out.println("2> Saving Account \n");
        System.out.println("Please input your choice:");
        Scanner userCreate = new Scanner(System.in);
        return userCreate.nextInt();
}
    public static int inputDeposit() {
        System.out.println("Please input starting deposit:");
        Scanner userInitial = new Scanner(System.in);
        return userInitial.nextInt();
}
    public static void main(String[] args) {
        ArrayList<BankAccount> bankList = new ArrayList<>();
        System.out.println("-------------------------------");
        System.out.println("Welcome to Bank Account System");
        System.out.println("-------------------------------");
        int password = 0;
        int tries = 0;
        int acctNumber = 1;
        double rate = 0;
        Scanner scanner = new Scanner(System.in);
        while (password != 12345 && tries < 3) {
            try {
                System.out.println("Enter your password: ");
                password = scanner.nextInt();
                if (password != 12345) {
                    System.out.println("Wrong password!");
                    tries++;

         }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input! Please enter numbers only.");
        tries++;
        scanner.next();
} }
if (tries >= 3) {
    System.out.println("Try again later!!");
} else {
    System.out.println("Correct Password!");
    int userChoice;
    do {
        userChoice = menu();
        if (userChoice <= 0 || userChoice > 6) {
            System.out.println("Invalid Re-enter choice: ");
            userChoice = menu();
}
        if (userChoice == 1) {
            if (bankList.isEmpty()) {
                System.out.println("No accounts are present please create accounts first!");
            } else {
                System.out.println("Acc#     Type           Balance     Interest Rate");
                for (BankAccount i : bankList) {
                    System.out.println(i.toString());
                    System.out.println();
} }
        }
        if (userChoice == 2) {
            int acctCreate = accountCreator();
            while (acctCreate != 1 && acctCreate != 2) {
                System.out.println("Not valid please select from menu below:");
                acctCreate = accountCreator();
}
            if (acctCreate == 1) {
                int initialDeposit = inputDeposit();
                CheckingAccount newAcct = new CheckingAccount(acctNumber++, "Checking", initialDeposit, 0);
                bankList.add(newAcct);
                System.out.println("Account Saved!");
            }
            if (acctCreate == 2) {
                int initialDeposit = inputDeposit();
                System.out.println("Use the default interest rate (0.3%) ? Y/N?");
                Scanner interestRate = new Scanner(System.in);
                String storeRate = interestRate.next();
                if (storeRate.equals("Y") || storeRate.equals("y")) {
                    rate = 0.3;
                }
                if (storeRate.equals("N") || storeRate.equals("n")) {
                    System.out.println("Enter your interest rate:");
                    Scanner rateFromUser = new Scanner(System.in);
                    rate = rateFromUser.nextInt();
                }
                SavingsAccount newAcct = new SavingsAccount(acctNumber++, "Saving  ", initialDeposit, rate);
                bankList.add(newAcct);
                System.out.println("Account Saved!");
}
        }
        if (userChoice == 3) {
            if (bankList.isEmpty()) {
                System.out.println("No accounts are present please create accounts first!\n");
} else {
                for (BankAccount i : bankList) {
                    System.out.println(i.toString());
                }
                System.out.print("Enter account number to deposit into: ");
                Scanner getAcct = new Scanner(System.in);
                int depositActNum = getAcct.nextInt();
                int checkAccount = 0;
                for (BankAccount i : bankList) {

             if(i.getAcctNumber() == depositActNum) {
                System.out.print("Enter amount to deposit: ");
                Scanner getAmount = new Scanner(System.in);
                int amount = getAmount.nextInt();
                i.deposit(depositActNum,amount);
                checkAccount = 1;
                break;
} }
        if(checkAccount != 1) {
            System.out.println("Account Number Not Found");
} }
}
if (userChoice == 4) {
    if (bankList.isEmpty()) {
        System.out.println("No accounts are present please create accounts first!\n");
} else{
        for (BankAccount i : bankList) {
            System.out.println(i.toString());
        }
        System.out.print("Enter account number to withdraw from: ");
        Scanner getAcct = new Scanner(System.in);
        int withdrawActNum = getAcct.nextInt();
        int checkAccount = 0;
        for (BankAccount i : bankList) {
            if(i.getAcctNumber() == withdrawActNum) {
                System.out.print("Enter amount to withdraw: ");
                Scanner getAmount = new Scanner(System.in);
                int amount = getAmount.nextInt();
                i.withdraw(withdrawActNum,amount);
                checkAccount = 1;
break; }
        }
        if(checkAccount != 1) {
            System.out.println("Account Number Not Found");
        }
} }
if (userChoice == 5) {
    if (bankList.isEmpty()) {
        System.out.println("No accounts are present please create accounts first!\n");
    }
    else {
        System.out.println("Acc#     Type           Balance     Interest Rate");
        for (BankAccount i : bankList) {
            System.out.println(i.toString());
            System.out.println();
        }
        System.out.print("Enter account number to process a check: (remember needs to be a checking account)\n ");
        Scanner getAcct = new Scanner(System.in);
        int proCheckAcct = getAcct.nextInt();
        for (BankAccount i : bankList) {
            if (i.getAcctNumber() == proCheckAcct && i.getAcctType().equals("Checking")) {
                i.processCheck();
break; }
            else {
                System.out.println("account not present and or account is not a checking account");
                System.out.println("Acc#     Type           Balance     Interest Rate");
                for (BankAccount a : bankList) {
                    System.out.println(a.toString());
                    System.out.println();
                }
                System.out.print("Enter account number to process a check: (remember needs to be a checking account)\n ");
                getAcct = new Scanner(System.in);
                proCheckAcct = getAcct.nextInt();
                for (BankAccount a : bankList) {
                    if (i.getAcctNumber() == proCheckAcct && i.getAcctType().equals("Checking")) {
                        a.processCheck();
break; }
                    else {
                        System.out.println("Not able to cache check");
} }
} }
}

 } }
            while (userChoice < 6 && userChoice > 0);
            if (userChoice == 6) {
                System.out.println("Thanks for visiting the bank have a nice day!");
            }
} }
}
