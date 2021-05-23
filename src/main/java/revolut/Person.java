package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();
    private HashMap<String, Crypto> cryptoAccounts = new HashMap<String, Crypto>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        Crypto bitcoinAccount = new Crypto("Bitcoin", 0);
        userAccounts.put("EUR", euroAccount);
        cryptoAccounts.put("BTC", bitcoinAccount);

    }

    public void setAccountBalance(double startingBlanace) {
        userAccounts.get("EUR").setBalance(startingBlanace);
    }

    public void setBitcoinBalance(double startingBalance) {
        cryptoAccounts.get("BTC").setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    public Crypto getCryptoAccount(String account) {
        return cryptoAccounts.get(account);
    }
}
