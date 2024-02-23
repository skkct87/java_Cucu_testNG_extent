package Runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    public static WebDriver driver;
    public final static int TIMEOUT = 10;

    public static void tearUp() {


        if (driver == null) {

        /*    if (browserName == "firefox") {

            }
            else if (browserName == "edge") {

            }

            else {*/

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
                driver.manage().window().maximize();

          //  }
            driver.get("https://www.google.com/");

        }
    }

    public static void tearDown()  {
        driver.close();
        driver.quit();
    }
}
