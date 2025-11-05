//Author: Mahdi Bandali
//Date: 9/18/2025
//Project 1 
// This is my account interface that shows the different methods I will be calling in the child classes. 


public interface Account {
    void deposit(int acct, double  amount);
    void withdraw(int acct, double amount);
    void processCheck();
}

