package Runner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.directory;


@CucumberOptions(
        features = "src/test/resources/",
     //   tags = "try",
        glue = {"Steps", "Hooks"},
        monochrome = true,
        publish = true,
        plugin = { "pretty"
                ,"html:target/cucumber-report/cucumber.html" ,
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

        }



)

@Listeners
public class TestRunner extends AbstractTestNGCucumberTests {

        public static String projectpath = System.getProperty("user.dir");

        public static WebDriver driver = null;
        static String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());


@BeforeSuite
        public void SuiteStart() throws IOException {
        BaseClass.tearUp();
}

    @BeforeSuite
    public void deleteExtraFiles() throws IOException {
    Path directory = Path.of(projectpath + "//");
        for (File f : Objects.requireNonNull(directory.toFile().listFiles())) {
            if (f.getName().startsWith("runScenario_")) {
                System.out.println("!!  Files found     !!");
                f.delete();
                System.out.println("!!  Files deleted successfully     !!");
            }
        }
    }

   /* @AfterMethod
    public void GetFailedScreenshot(ITestResult scenario) throws IOException
    {
        WebDriver driver = BaseClass.driver;
        if(scenario.getStatus() == ITestResult.FAILURE)
        {
          try{
                System.out.println("!! Test step failed , Proceed to take screenshot     !!");
                String dateName1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                String testname = scenario.getName() + "_"+ dateName1;
                ExtentReports extent = new ExtentReports(testname);
                ExtentTest logger = new ExtentTest(testname , "Logger");
                logger.log(LogStatus.FAIL, "Test Case Failed is "+scenario.getName());
                logger.log(LogStatus.FAIL, "Test Case Failed is "+scenario.getThrowable());
              ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot(driver , testname)).build());
              System.out.println("!!    Screenshot taken     !!");
                extent.endTest(logger);
                extent.flush();
                extent.close();
            }
            catch (IOException e){
                System.out.println("!!    Screenshot not taken     !!");

            }
        }
        else if(scenario.getStatus() == ITestResult.SKIP)
        {
            System.out.println("!! Test step skipped    !!" + scenario.getName());
        }

    }
*/

            @AfterSuite
            public void SuiteTested () {
            //    extent.endTest(logger);
            //    extent.flush();
             //   extent.close();
                BaseClass.tearDown();
            }


            public String getBase64Screenshot (WebDriver driver, String testCaseName ) throws IOException {
                String Base64StringofScreenshot = "";
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                // for saving screenshots in local - this is optional
                Date oDate = new Date();
                SimpleDateFormat oSDF = new SimpleDateFormat("ddMMYYYY_HHmmss");
                String sDate = oSDF.format(oDate);
                FileUtils.copyFile(src, new File(projectpath + "/target/test-output/screenshots/" + testCaseName + ".png"));
                //
                byte[] fileContent = FileUtils.readFileToByteArray(src);
                Base64StringofScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
                return Base64StringofScreenshot;
            }


            public String getScreenshot (WebDriver driver1, String testCaseName) throws IOException {
                String datetime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                TakesScreenshot ts = ((TakesScreenshot) driver1);
                File source = ts.getScreenshotAs(OutputType.FILE);
                String fileWithPath = projectpath + "/target/test-output/screenshots/" + testCaseName + ".png";
                File finalDestination = new File(fileWithPath);
                FileUtils.copyFile(source, finalDestination);
                return fileWithPath;
            }


        }
