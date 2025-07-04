package com.stratapps.xamplify.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.stratapps.xamplify.base.BaseTest;
import com.stratapps.xamplify.utils.ConfigReader;
import com.stratapps.xamplify.utils.EmailUtil;
import com.stratapps.xamplify.utils.ExtentManager;
import com.stratapps.xamplify.utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.FileWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestListener implements ITestListener, ISuiteListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static long suiteStartTime;
    public static long suiteEndTime;

    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
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
            driver = ((BaseTest) testClass).getDriver();
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
            BaseTest.failedScreenshotPaths.add(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ISuite suite) {
        suiteEndTime = System.currentTimeMillis();

        int passedCount = 0;
        int failedCount = 0;
        int skippedCount = 0;

        StringBuilder passedTests = new StringBuilder();
        StringBuilder failedTests = new StringBuilder();
        StringBuilder skippedTests = new StringBuilder();

        // New: Grouped by class name
        Map<String, int[]> classSummary = new LinkedHashMap<>();

        for (ISuiteResult result : suite.getResults().values()) {
            ITestContext context = result.getTestContext();

            passedCount += context.getPassedTests().size();
            failedCount += context.getFailedTests().size();
            skippedCount += context.getSkippedTests().size();

            accumulateClassSummary(classSummary, context.getPassedTests().getAllResults(), 1, 0, 0);
            accumulateClassSummary(classSummary, context.getFailedTests().getAllResults(), 0, 1, 0);
            accumulateClassSummary(classSummary, context.getSkippedTests().getAllResults(), 0, 0, 1);
        }

        // Write class-level summary to CSV
        try (FileWriter writer = new FileWriter("TestExecutionSummary.csv")) {
            writer.append("Name\tPassed\tFailed\tSkipped\tOthers\tPassed %\n");
            for (Map.Entry<String, int[]> entry : classSummary.entrySet()) {
                int passed = entry.getValue()[0];
                int failed = entry.getValue()[1];
                int skipped = entry.getValue()[2];
                int others = 0;
                int total = passed + failed + skipped;
                double passedPercentage = total == 0 ? 0 : ((double) passed / total) * 100;

                writer.append(entry.getKey()).append("\t")
                        .append(String.valueOf(passed)).append("\t")
                        .append(String.valueOf(failed)).append("\t")
                        .append(String.valueOf(skipped)).append("\t")
                        .append(String.valueOf(others)).append("\t")
                        .append(String.format("%.3f%%", passedPercentage)).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        String startTimeStr = sdf.format(new Date(suiteStartTime));
        String endTimeStr = sdf.format(new Date(suiteEndTime));
        long durationMillis = suiteEndTime - suiteStartTime;
        long durationMinutes = (durationMillis / 1000) / 60;
        long durationSeconds = (durationMillis / 1000) % 60;

        String machine = "Unknown";
        try {
            machine = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            machine = "Unavailable";
        }

        String browser = ConfigReader.getProperty("browser.name");
        String environment = ConfigReader.getProperty("env.name");

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("\uD83D\uDCCB Test Summary:\n")
                .append("\u2705 Passed: ").append(passedCount).append("\n")
                .append("\u274C Failed: ").append(failedCount).append("\n")
                .append("\u23ED️ Skipped: ").append(skippedCount).append("\n\n")
                .append("\uD83D\uDD52 Start Time: ").append(startTimeStr).append("\n")
                .append("\uD83D\uDD53 End Time: ").append(endTimeStr).append("\n")
                .append("\u23F1️ Duration: ").append(durationMinutes).append(" min ").append(durationSeconds).append(" sec\n\n")
                .append("\uD83D\uDCBB Machine: ").append(machine).append("\n")
                .append("\uD83C\uDF10 Browser: ").append(browser != null ? browser : "Not Set").append("\n")
                .append("\uD83C\uDFF7️ Environment: ").append(environment != null ? environment : "Not Set").append("\n\n")
                .append("\uD83D\uDCC6 Execution details exported to TestExecutionSummary.csv\n\n")
                .append("\uD83D\uDCCC Please find the attached test execution report and any screenshots.");

        String subject = "\uD83D\uDCE7 Automation Test Report - Summary";
        String reportPath = ExtentManager.getReportPath();

        ExtentManager.flushReport();

        EmailUtil.sendReportEmailWithAttachments(
                "agayatri@stratapps.com",
                subject,
                emailBody.toString(),
                reportPath,
                BaseTest.failedScreenshotPaths
        );
    }

    private void accumulateClassSummary(Map<String, int[]> summary, Set<ITestResult> results, int p, int f, int s) {
        for (ITestResult result : results) {
            String className = result.getTestClass().getName();
            summary.putIfAbsent(className, new int[3]);
            int[] counts = summary.get(className);
            counts[0] += p;
            counts[1] += f;
            counts[2] += s;
        }
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onTestFailedWithTimeout(ITestResult result) {}
}
