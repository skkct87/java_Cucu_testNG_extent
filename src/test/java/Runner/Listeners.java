package Runner;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


public class Listeners extends TestRunner implements ITestListener {


    static String projectpath = System.getProperty("user.dir");

    @Override
    public void onFinish(ITestContext Result) {
        Reporter.log("Test Process Finished ");
        System.out.println("Test Process Finished ");
    }

    @Override
    public void onStart(ITestContext Result) {
        Reporter.log("Test Process Starting ");
        System.out.println("Test Process Starting ");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

    }



    @After
    public void onTestFailure(ITestResult Result) {
        Reporter.log("Test Case Failed");
        
    //    System.out.println("Test Case Failed");
     /*   try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getSS(Result.getTestName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        //   ExtentCucumberAdapter.addTestStepScreenCaptureFromPath
    //    Allure.addAttachment(Result.getTestName(), new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
     //   System.out.println("Screenshot capture for Allure Report");
        //getScreenshot();

    }


    @Override
    public void onTestSkipped(ITestResult Result) {
        Reporter.log("Test Skipped");
        System.out.println("Test Skipped");
    }

    @Override
    public void onTestStart(ITestResult Result) {
        Reporter.log(Result.getName() + " Test Case Excution Started");
        System.out.println("Test Case Excution Started");

    }

    @Override
    public void onTestSuccess(ITestResult Result) {
        Reporter.log("Test Passed");
        System.out.println("Test Passed");

    }

    public String getSS( String filename) {
        this.driver = driver;
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = projectpath + "\\target\\screenshots\\" + filename + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;
    }

}
