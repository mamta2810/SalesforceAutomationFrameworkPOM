package com.salesforce.automation.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {
	static LocalDateTime date = LocalDateTime.now();
	static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
	static String dateTime = dateFormat.format(date);
	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String USER_PROPERTIES_PATH=USER_DIR+"/src/test/resources/user.properties";
	public static final String APPLICATION_PROPERTIES_PATH = USER_DIR+"/src/test/resources/application.properties";
	public static final String GENERATE_NEW_REPORT_PATH= USER_DIR+"/ExtentReport/"+ dateTime + "extentReport.html";
}
