package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    PaymentService transactionService;

    @Before//Before hooks run before the first step in each scenario
    public void setUp() {
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        transactionService = new PaymentService("TestService");
    }

    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny does have enough money in his account to top up")
    public void danny_does_have_enough_money_in_his_account_to_top_up() {
        topUpMethod.transactionSuccess();
    }

    @Given("Danny has a starting balance of {double}")
    public void dannyHasAStartingBalanceOf(double startBalance){
        System.out.println("Danny has starting account balance of: " + String.valueOf(startBalance));
        danny.setAccountBalance(startBalance);
    }

    @Given("Danny does not have enough money in his account to top up")
    public void dannyIsBroke(){
        topUpMethod.transactionFail();
        System.out.println("was the transaction succesful? " + topUpMethod.isTransactionStatus());
    }


    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }


    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount, transactionService.isTransactionStatus());
        //throw new io.cucumber.java.PendingException();
    }

    @When("Danny now tops up by {double}")
    public void danny_tops_up_by(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount, transactionService.isTransactionStatus());
        //throw new io.cucumber.java.PendingException();
    }

    @When("Danny now tries to to up by {double}")
    public void danny_tries_to_top_up_by(double topUpAmount){
        transactionService.transactionFail();
        System.out.println("still keeping status? expect false: " + transactionService.isTransactionStatus());
        danny.getAccount("EUR").addFunds(topUpAmount, transactionService.isTransactionStatus());
    }

//reusing same logic makes life easy
    @Then("The balance in his euro account should be {double}")
    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0);
        System.out.println("The new final balance is: " + actualResult);
    }



}


