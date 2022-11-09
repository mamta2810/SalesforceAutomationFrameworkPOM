package com.salesforce.automation.scripts;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.automation.utilities.GenerateReports;

public class ListenerClass implements ITestListener {
	GenerateReports report = GenerateReports.getInstance();;

	public void onTestFailure(ITestResult result) {

		try {
			report.logTestFailed(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSuccess(ITestResult result) {

		report.logTestPass(result.getName());

	}

}
