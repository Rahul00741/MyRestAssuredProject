package com.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
//import io.restassured.response.Response;
//import java.util.logging.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class BaseClass {
	
	public static Properties properties;
	public static Logger logger;
	public static String uri;
	
//	PrintStream log = new PrintStream(new File("./Log File/Log.txt"));
	public static void BaseSetup() throws IOException
	{
		
	properties = new Properties();
	
		FileInputStream file = new FileInputStream("./src/test/resources/properties/config.properties");
		properties.load(file);
		
		logger = Logger.getLogger("RestAssuredFramework");
		PropertyConfigurator.configure("./src/test/resources/Log4jProperty/log4j.properties");
		uri = properties.getProperty("url");
	}
	}
	

