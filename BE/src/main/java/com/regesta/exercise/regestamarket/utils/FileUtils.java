package com.regesta.exercise.regestamarket.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private FileUtils() {
	    throw new IllegalStateException("Utility class");
    }
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * Returns a property file from the visible path.
	 * @param name The file to be searched. It has to be reachable by the classloader.
	 * @return The content of requested the property file.
	 * @throws FileNotFoundException If no file has been found.
	 * @throws IOException If no file has been found.
	 */
	public static Properties loadPropertiesFile(String name) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		URL url = FileUtils.getAsUrl(name);
		if(url != null){
			InputStream stream = null;
			try{
				stream = url.openStream();
				p.load(stream);
			} finally{
				if(stream != null){
					try{
						stream.close();
					} catch(IOException e){
						logger.error("An error has occurred while closing the stream.");
					}
				}
			}
		} else {
			throw new FileNotFoundException("The requested property file is not available: " + name);
		}
		return p;
	}
	
	/**
	 * Given an input name return the corresponding URL to the file.
	 * @param name The path to format.
	 * @return The formatted URL.
	 */
	public static URL getAsUrl(String name){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(name);
	}
	
	
	/**
	 * Transforms a byte array into an md5 string.
	 * @param bytes The bytes to be transformed.
	 * @return The md5 string.
	 */
	public static String md5(byte[] bytes) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytes);
		
			return bytesToHex(thedigest);
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Transforms a byte array into an hex string.
	 * @param bytes The bytes to be transformed.
	 * @return The md5 string.
	 */
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
}
