package com.salesforce.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtilities {

	public FileInputStream inputFile;
	
	public Properties loadFile(String fileName) {
		String filePath = null;
		Properties propertyFile = new Properties();
		
		switch (fileName) {
		case "user":
			filePath = Constants.USER_PROPERTIES_PATH;
			break;

		case "application":
			filePath = Constants.APPLICATION_PROPERTIES_PATH;
			break;

		}

		try {
			inputFile = new FileInputStream(filePath);
			propertyFile.load(inputFile);
			}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return propertyFile;
	}
		
		public static String  getApplicationProperty(String key, Properties propertyFile) {
			String value=propertyFile.getProperty(key);
			System.out.println("Property from file is " + value);
			/*try {
			//	inputFile.close();
				System.out.println("got no exception");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("got an exception");
			}
			*/
			return value;
		}

	}
