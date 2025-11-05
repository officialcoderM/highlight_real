//Author: Mahdi Bandali
//September 18, 2025
//Professor Gao
//COS-210-010
//Project 1
//This is the interface class which has three abstract methods deposit, withdraw, and processCheck.
public interface Account {
    void deposit(int acct, double  amount);
    void withdraw(int acct, double amount);
    void processCheck();
}
