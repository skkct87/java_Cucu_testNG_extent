package Hooks;

import Runner.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static Runner.TestRunner.projectpath;

public class hooks {


    @After
    public void CloseBrowser(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            WebDriver driver1 = new BaseClass().driver;
            String testCaseName = scenario.getName();
            System.out.println("Proceed to taking screenshot for failed scenario...!!!");
            TakesScreenshot ts = (TakesScreenshot) driver1;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String fileWithPath = projectpath + "/target/test-output/screenshots/" + testCaseName + ".png";
            File finalDestination = new File(fileWithPath);
            FileUtils.copyFile(source, finalDestination);
           scenario.attach(ts.getScreenshotAs(OutputType.BYTES),"image/png" , scenario.getName() );
            System.out.println("!!    Screenshot taken and attached to scenario    !!");
              }
    }
}
