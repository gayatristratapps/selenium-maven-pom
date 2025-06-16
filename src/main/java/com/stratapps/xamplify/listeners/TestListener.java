package com.stratapps.xamplify.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.stratapps.xamplify.base.BaseTest;
import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.EmailUtil;
import com.stratapps.xamplify.utils.ExtentManager;
import com.stratapps.xamplify.utils.ScreenshotUtil;
import com.stratapps.xamplify.utils.ElementUtil;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static long suiteStartTime;
    public static long suiteEndTime;

    // Called when suite starts
    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
    }

    // Called when each test starts
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance()
                .createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = null;

        if (testClass instanceof BaseTest) {
            driver = ((BaseTest) testClass).getDriver(); // ✅ get WebDriver
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

            // Add to Extent report
            extentTest.get().addScreenCaptureFromPath(screenshotPath);

            // ✅ Store screenshot path in BaseTest's global list
            BaseTest.failedScreenshotPaths.add(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip("Test skipped");
    }

    // Called when suite finishes
    @Override
    public void onFinish(ISuite suite) {
        suiteEndTime = System.currentTimeMillis();

        int passedCount = 0;
        int failedCount = 0;
        int skippedCount = 0;

        StringBuilder passedTests = new StringBuilder();
        StringBuilder failedTests = new StringBuilder();
        StringBuilder skippedTests = new StringBuilder();

        for (ISuiteResult result : suite.getResults().values()) {
            ITestContext context = result.getTestContext();

            // Count totals
            passedCount += context.getPassedTests().size();
            failedCount += context.getFailedTests().size();
            skippedCount += context.getSkippedTests().size();

            for (ITestResult r : context.getPassedTests().getAllResults()) {
                passedTests.append("  - ").append(r.getMethod().getMethodName()).append("\n");
            }

            for (ITestResult r : context.getFailedTests().getAllResults()) {
                failedTests.append("  - ").append(r.getMethod().getMethodName()).append("\n");
            }

            for (ITestResult r : context.getSkippedTests().getAllResults()) {
                skippedTests.append("  - ").append(r.getMethod().getMethodName()).append("\n");
            }
        }

        // Time formatting
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String startTimeStr = sdf.format(new Date(suiteStartTime));
        String endTimeStr = sdf.format(new Date(suiteEndTime));
        long durationMillis = suiteEndTime - suiteStartTime;
        long durationMinutes = (durationMillis / 1000) / 60;
        long durationSeconds = (durationMillis / 1000) % 60;

        // Machine, browser, and environment info
        String machine = "Unknown";
        try {
            machine = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            machine = "Unavailable";
        }

        String browser = ConfigReader.getProperty("browser.name");
        String environment = ConfigReader.getProperty("env.name");

        // Email body construction
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("📋 Test Summary:\n")
                .append("✅ Passed: ").append(passedCount).append("\n")
                .append("❌ Failed: ").append(failedCount).append("\n")
                .append("⏭️ Skipped: ").append(skippedCount).append("\n\n")
                .append("🕒 Start Time: ").append(startTimeStr).append("\n")
                .append("🕓 End Time: ").append(endTimeStr).append("\n")
                .append("⏱️ Duration: ").append(durationMinutes).append(" min ").append(durationSeconds).append(" sec\n\n")
                .append("💻 Machine: ").append(machine).append("\n")
                .append("🌐 Browser: ").append(browser != null ? browser : "Not Set").append("\n")
                .append("🏷️ Environment: ").append(environment != null ? environment : "Not Set").append("\n\n")

                .append("✅ Passed Tests:\n").append(passedTests.length() > 0 ? passedTests.toString() : "  - None\n").append("\n")
                .append("❌ Failed Tests:\n").append(failedTests.length() > 0 ? failedTests.toString() : "  - None\n").append("\n")
                .append("⏭️ Skipped Tests:\n").append(skippedTests.length() > 0 ? skippedTests.toString() : "  - None\n").append("\n")

                .append("📎 Please find the attached test execution report and any screenshots.");

        String subject = "📧 Automation Test Report - Summary";
        String reportPath = ExtentManager.getReportPath();

        // Flush the Extent report
        ExtentManager.flushReport();

		
		  EmailUtil.sendReportEmailWithAttachments(
		  "agayatri@stratapps.com,gayatri@xamplify.com", // 🔁 Add more as needed
		  subject, emailBody.toString(), reportPath, BaseTest.failedScreenshotPaths );
		 
    }

    // Unused but required methods
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) {}
}
