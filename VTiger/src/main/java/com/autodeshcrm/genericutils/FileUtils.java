package com.autodeshcrm.genericutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
/**
 * 
 * @author Shravya
 *
 */
public class FileUtils {
	/**
	 * This method is used to read data from Property File
	 * @param key
	 * @return
	 * @throws Throwable 
	 * @throws Throwable
	 */
public String getPropertyKeyValue(String key) throws Throwable
{
	FileInputStream file=new FileInputStream("./resources/commonData.properties");
	Properties prop=new Properties();
	prop.load(file);
	String value=prop.getProperty(key);
    return value;
	
	
}
}
