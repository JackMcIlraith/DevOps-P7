package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class CryptoStepDef {

    private double topUpAmount;
    private double exchangeValueEuro;
    private double exchangeValueBitcoin;
    PaymentService internalExchange = new PaymentService("Internal Transactions");
    Person charles;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Before
    public void setUp() {
        charles = new Person("Charles");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("Charles has {double} euro in his euro Revolut account")
    public void charlesHasEuroInHisEuroRevolutAccount(double startingBalance) {
        charles.setAccountBalance(startingBalance);
    }

    @And("Charles has {double} bitcoin in his bitcoin crypto Revolut account")
    public void charlesHasBitcoinInHisBitcoinCryptoRevolutAccount(double startingCrypto) {
        charles.setBitcoinBalance(startingCrypto);
    }

    @And("Charles selects {double} euro as the exchange amount")
    public void charlesSelectsEuroAsTheExchangeAmount(double exchangeAmountInEuro) {
        this.exchangeValueEuro = exchangeAmountInEuro;
    }

    @When("Charles exchanges for bitcoin")
    public void charlesExchanges() {
        System.out.println(exchangeValueEuro);
        //remove 100 euro from euro account
        charles.getAccount("EUR").removeFunds(exchangeValueEuro, internalExchange.isTransactionStatus());
        //add 100e equivalent to bitcoin account:
        charles.getCryptoAccount("BTC").buyCryptoFromEuro(exchangeValueEuro,internalExchange.isTransactionStatus());
    }

    @And("Charles new balance of his bitcoin crypto account should now be {double}")
    public void theNewBalanceOfHisBitcoinCryptoAccountShouldNowBe(double cryptoBalance) {
        double actualResult = charles.getCryptoAccount("BTC").getBalance();
        //Assert
        Assert.assertEquals(cryptoBalance, actualResult, 0);
        System.out.println("The new bitcoin balance is: " + actualResult);
    }

    @Then("Charles new balance of his euro account should now be {double}")
    public void charlesNewBalanceOfHisEuroAccountShouldNowBe(double euroBalance) {
        double actualResult = charles.getAccount("EUR").getBalance();
        //Assert
        //assert equal within bounds of rounding error
        Assert.assertEquals(euroBalance, actualResult, 0.01);
        System.out.println("The new euro balance is: " + actualResult);
    }

    @And("Bitcoins exchange rate is {double}")
    public void bitcoinsExchangeRateIs(double exchangeRate) {
        charles.getCryptoAccount("BTC").setExchangeRate(exchangeRate);
    }

    @And("Charles selects {double} bitcoin as the exchange amount")
    public void charlesSelectsExchangeAmountInBitcoinBitcoinAsTheExchangeAmount(double exchangeAmountInBitcoin) {
        this.exchangeValueBitcoin = exchangeAmountInBitcoin;
    }

    @When("Charles exchanges for euro")
    public void charlesExchangesForEuro() {
        System.out.println("we will now sell: " + exchangeValueBitcoin + " Bitcoin(s)");
        //remove bitcoin and determine euro value from euro account
        double euroReceived = charles.getCryptoAccount("BTC").sellCryptoFromEuro(exchangeValueBitcoin, internalExchange.isTransactionStatus());
        System.out.println("The value exchanged was: " + euroReceived);
        //add 100e to EUR account:
        charles.getAccount("EUR").addFunds(euroReceived, internalExchange.isTransactionStatus());
    }

    @And("Charles has sufficient funds in Euro")
    @And("Charles has insufficient funds in Euro Account, is rejected by payment service")
    public void charlesHasInsufficientFundsIsRejectedByPaymentService() {
        this.internalExchange.isTransactionSuccessful(charles.getAccount("EUR").getBalance(), exchangeValueEuro);
    }

    @And("Charles has sufficient funds in Bitcoin")
    @And("Charles has insufficient funds in Bitcoin Account, is rejected by payment service")
    public void charlesHasInsufficientFundsIsRejectedByPaymentServiceBitcoin() {
        this.internalExchange.isTransactionSuccessful(charles.getCryptoAccount("BTC").getBalance(), exchangeValueBitcoin);
    }
}
