//Author: Mahdi Bandali
//September 18, 2025
//Professor Gao
//COS-210-010
//Project 1
//This is my child class for the BankAccount which uses the BankAccount constuctor via super and the
//BankAccount toString() via super.
public class CheckingAccount extends BankAccount {
        public CheckingAccount(int acctNumberMain, String acctTypeMain, float balanceMain, double interestRateMain) {
                super(acctNumberMain, "Checking", balanceMain, interestRateMain);
        }
        public String toString() {
                return super.toString();
        } }
