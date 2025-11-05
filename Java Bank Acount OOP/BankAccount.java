//Author: Mahdi Bandali
//September 18, 2025
//Professor Gao
//COS-210-010
//Project 1
//This is my BankAccount class which implements the funcitions from the interface.
//It also has two getter functions getAcctNumber and getAcctType which are needed to check if the account the user
//selects to withdraw or deposit from is present in the banklist and to check if the account is a checking account to
//proccess a check.
//They are called in the main method called BankMain


import java.util.Scanner;

public class BankAccount implements Account {
    private String acctType;
    private double balance;
    private double interestRate;
    private int acctNumber;

    public BankAccount(int acctNumberMain, String acctTypeMain, float balanceMain, double interestRateMain ){
        this.acctNumber =acctNumberMain;
        this.acctType = acctTypeMain;
        this.balance = balanceMain;
        this.interestRate = interestRateMain;
    }
    @Override
    public void deposit(int acct, double amount) {
        if (amount <= 0){
            System.out.println("invalid please enter amount greater then zero: ");
        }
        else {
            balance = balance + amount;
            System.out.println("Successful deposit: " + amount);
        }
    }
    @Override
    public void withdraw(int acct, double amount) {
        if (balance < amount){
            System.out.println("Sorry you cannot withdraw more then you have in the acct.");

        }
        else{
            balance = balance -amount;
            System.out.println("Successful withdraw: " + amount);
        }
    }
    @Override
    public void processCheck() {
        System.out.print("Please input the check #: ");
        Scanner userCheckNumber = new Scanner(System.in);
        int userCheck = userCheckNumber.nextInt();
        System.out.print("Please input the amount: ");
        Scanner amountUser = new Scanner(System.in);
        double amountFromUser = amountUser.nextDouble();

        if (balance < amountFromUser){
            System.out.println("Sorry you cannot process a check more then you have in the acct.");
        }
        else{
            balance = balance -amountFromUser;
            System.out.println("Successfully cached check number " +userCheck + " for the amount: "+ amountFromUser);
        }
    }
    public int getAcctNumber() {
        return acctNumber;
    }
    public String getAcctType() {
        return acctType;
    }
    public String toString() {
        return acctNumber + "       " + acctType + "        " + balance + "        " + interestRate + "%";
    }
}

