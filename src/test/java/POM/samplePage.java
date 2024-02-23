package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class samplePage {

    WebDriver driver;


    public samplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


   /* public dashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
*/

    @FindBy(css ="#APjFqb" )
    public WebElement searchBox;

    @FindBy(className = "gL9999Fyf")
    public WebElement topresultitem;
}
