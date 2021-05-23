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
    PaymentService internalExchange = new PaymentService("Internal Transactions");
    Person charles;

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
    public void dannyHasBitcoinInHisBitcoinCryptoRevolutAccount(double startingCrypto) {
        charles.setBitcoinBalance(startingCrypto);
    }

    @And("Charles selects {double} euro as the exchange amount")
    public void dannySelectsEuroAsTheExchangeAmount(double exchangeAmountInEuro) {
        this.exchangeValueEuro = exchangeAmountInEuro;
    }

    @When("Charles exchanges for bitcoin")
    public void dannyExchanges() {
        //transaction was successful:
        this.internalExchange.transactionSuccess();
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
        Assert.assertEquals(euroBalance, actualResult, 0);
        System.out.println("The new euro balance is: " + actualResult);
    }
}
