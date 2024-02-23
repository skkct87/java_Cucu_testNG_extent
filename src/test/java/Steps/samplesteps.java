package Steps;
import POM.samplePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.*;


import java.io.IOException;

import static Runner.BaseClass.driver;



public class samplesteps {

    samplePage lp = new samplePage(driver);

    @Given("User at google home page")
    public void user_at_google_home_page() {

        System.out.println("Open google page");
    }

    @When("User enter {string}")
    public void user_enter(String keyword) {
        lp.searchBox.sendKeys(keyword);
        lp.searchBox.sendKeys(Keys.ENTER);
        System.out.println("Keyword entered");
    }
    @Then("User should see the result")
    public void user_should_see_the_result() throws IOException {
        System.out.println("Result found");
        String searchtext = lp.searchBox.getText();
        String resulttext = lp.topresultitem.getText();
        Assert.assertTrue(resulttext.contains(searchtext),"Result not matched with keyword");

    }
}
