package fundtransfer.test;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FundTransferStepDefs {
    protected WebDriver driver;
    private FundTransferPage fundTransferPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        fundTransferPage = new FundTransferPage(driver);
    }

    @Given("the user is on Fund Transfer Page")
    public void user_is_on_fund_transfer_page() {
        driver.get("http://dl.dropbox.com/u/55228056/fundTransfer.html");
    }

    @When("he enters \"([^\"]*)\" as payee name")
    public void user_enters_payee_name(String payeeName) {
        fundTransferPage.payeeField.sendKeys(payeeName);
    }

    @And("he enters \"([^\"]*)\" as amount")
    public void user_enters_amount(String amount) {
        fundTransferPage.amountField.sendKeys(amount);
    }

    @And("he Submits request for Fund Transfer")
    public void He_submits_request_for_fund_transfer() {
        fundTransferPage.transferButton.click();
    }

    @Then("ensure the fund transfer is complete with \"([^\"]*)\" message")
    public void ensure_the_fund_transfer_is_complete(String msg) {
        assertEquals(msg, fundTransferPage.messageLabel.getText());
    }

    @Then("ensure a transaction failure message \"([^\"]*)\" is displayed")
    public void ensure_a_transaction_failure_message(String msg) {
        assertEquals(msg, fundTransferPage.messageLabel.getText());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
