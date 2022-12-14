package com.salesforce.automation.utilities;


import com.aventstack.extentreports.ExtentReports;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.salesforce.automation.pages.base.BasePage;

public class GenerateReports {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	private static GenerateReports ob;
	
	private GenerateReports() {
		
	}
	
	public static GenerateReports getInstance() {
		if(ob==null) {
			ob=new GenerateReports();
			System.out.println("new report created");
		}
			return ob;
	}

	public static void startExtentReport() {
		
		htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_NEW_REPORT_PATH);
		System.out.println("path set up");
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("Salesforce Automation");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Host Name", "Salesforce");
		extentReport.setSystemInfo("Environment", "Automation Testing");
		extentReport.setSystemInfo("UserName","Mamta");
		
	}
		
	public static void StartSingleTestReport(String testName) {
		System.out.println("test created in extent report");
		test=extentReport.createTest(testName);
		}
	
	public void logTestInfo(String message) {
		test.log(Status.INFO, message);
	}
	
	public void logTestPass(String testName) {
	
		test.log(Status.PASS,MarkupHelper.createLabel(testName + " is passed.", ExtentColor.GREEN));
	}
	
	public void logTestFail(String testName) {
	
	test.log(Status.FAIL, MarkupHelper.createLabel(testName + "is failed", ExtentColor.RED));
	}
		
public void logTestFailed(String testName) throws IOException {
		
		String pathSS =BasePage.takeScreenshot(testName);
		System.out.println("Screenshot is at location " +pathSS);
		test.log(Status.FAIL, testName + " is failed ", MediaEntityBuilder.createScreenCaptureFromPath(pathSS).build());
		
			
	}
	public void endReport() {
		extentReport.flush();
	}

	}


