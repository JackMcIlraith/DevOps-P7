package revolut;
import java.lang.Math;

import java.util.Currency;

public class Crypto {
    private String cryptoCurrency;
    private double balance;
    private double exchangeRate = 1; //default rate to be updated as required

    public Crypto(String currency, double balance){
        this.balance = balance;
        this.cryptoCurrency = currency;
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

    public void setExchangeRate(double rate) {
        this.exchangeRate = rate;
    }

    public void buyCryptoFromEuro(double amountToExchange, boolean isTransactionSuccessful) {
        double amountInCrypto = amountToExchange * exchangeRate;
        amountInCrypto*=1000000;
        amountInCrypto = Math.round(amountInCrypto);
        amountInCrypto/=1000000;
        addFunds(amountInCrypto,isTransactionSuccessful);
    }

    public double sellCryptoFromEuro(double amountOfCryptoToExchange, boolean isTransactionSuccessful) {
        double amountInEuro = amountOfCryptoToExchange / exchangeRate;
        System.out.println(amountOfCryptoToExchange + " bitcoin is worth: " + amountInEuro + " Euro");
        amountInEuro*=100;
        System.out.println("Still worth: " + amountInEuro);
        amountInEuro = Math.round(amountInEuro);
        amountInEuro/=100;
        System.out.println("Still worth: " + amountInEuro);
        removeFunds(amountOfCryptoToExchange,isTransactionSuccessful);
        return amountInEuro;
    }
}
