package revolut;

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

    public void setExchangeRate(double rate) {
        this.exchangeRate = rate;
    }

    public void buyCryptoFromEuro(double amountToExchange, boolean isTransactionSuccessful) {
        double amountInCrypto = amountToExchange / exchangeRate;
        addFunds(amountInCrypto,isTransactionSuccessful);
    }

    public double sellCryptoFromEuro(double amountOfCryptoToExchange, boolean isTransactionSuccessful) {
        double amountInEuro = amountOfCryptoToExchange * exchangeRate;
        addFunds((amountOfCryptoToExchange*-1),isTransactionSuccessful);
        return amountInEuro;
    }
}
