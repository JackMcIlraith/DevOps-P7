package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount, boolean isTransactionSuccessful) {
        if(isTransactionSuccessful) {
            this.balance += topUpAmount;
        }
    }

    public void removeFunds(double removeAmount, boolean isTransactionSuccessful) {
        if(isTransactionSuccessful) {
            this.balance -= removeAmount;
        }
    }
}
