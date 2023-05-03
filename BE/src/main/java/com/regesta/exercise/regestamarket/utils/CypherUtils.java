package com.regesta.exercise.regestamarket.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author JavaDigest
 * 
 */
public class CypherUtils {
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	

	/**
	 * String to hold name of the encryption algorithm.
	 */
	public static final String ALGORITHM = "RSA";


  /**
   * Generate key which contains a pair of private and public key using 1024
   * bytes. Store the set of keys in Prvate.key and Public.key files.
   * 
   * @throws NoSuchAlgorithmException
   * @throws IOException
   * @throws FileNotFoundException
   */
  	public static void generateKey(String privateFilePath, String publicFilePath) {
  		try {
  				final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
  				keyGen.initialize(1024);
  				final KeyPair key = keyGen.generateKeyPair();

  				File privateKeyFile = new File(privateFilePath);
  				File publicKeyFile = new File(publicFilePath);

  				// Create files to store public and private key
  				if (privateKeyFile.getParentFile() != null) {
  					privateKeyFile.getParentFile().mkdirs();
  				}
  				privateKeyFile.createNewFile();

  				if (publicKeyFile.getParentFile() != null) {
  					publicKeyFile.getParentFile().mkdirs();
  				}
  				publicKeyFile.createNewFile();

  				// Saving the Public key in a file
  				ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
  				publicKeyOS.writeObject(key.getPublic());
  				publicKeyOS.close();

  				// Saving the Private key in a file
  				ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
  				privateKeyOS.writeObject(key.getPrivate());
  				privateKeyOS.close();
  		} 
  		catch (Exception e) {
  			logger.error(e.getMessage(), e);
  		}
  	}
  	
  	public static KeyPair generateKey(SecureRandom secureRandom) {
  		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			if(secureRandom != null) {
				keyGen.initialize(1024, secureRandom);
			}
			else {
				keyGen.initialize(1024);
			}
			final KeyPair keyPair = keyGen.generateKeyPair();
			
			return keyPair;
  		} 
  		catch (Exception ex) {
  			logger.error(ex.getMessage(), ex);
  		}
  		return null;
  	}

	  /**
	   * The method checks if the pair of public and private key has been generated.
	   * 
	   * @return flag indicating if the pair of keys were generated.
	   */
	  public static boolean areKeysPresent(String privateFilePath, String publicFilePath) {
	
		  File privateKey = new File(privateFilePath);
		  File publicKey = new File(publicFilePath);
	
		  if (privateKey.exists() && publicKey.exists()) {
			  return true;
		  }
		  return false;
	  }

	/**
	   * Encrypt the plain text using public key.
	   * 
	   * @param text
	   *          : original plain text
	   * @param key
	   *          :The public key
	   * @return Encrypted text
	   * @throws java.lang.Exception
	   */
	  public static byte[] encrypt(String text, Key key) {
		  byte[] cipherText = null;
		  try {
		      // get an RSA cipher object and print the provider
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);
		      // encrypt the plain text using the public key
		      cipher.init(Cipher.ENCRYPT_MODE, key);
		      cipherText = cipher.doFinal(text.getBytes());
		  } 
		  catch (Exception e) {
			  logger.error(e.getMessage(), e);
		  }
		  return cipherText;
	  }

	  /**
	   * Decrypt text using private key.
	   * 
	   * @param text
	   *          :encrypted text
	   * @param key
	   *          :The private key
	   * @return plain text
	   * @throws java.lang.Exception
	   */
	  public static String decrypt(byte[] text, Key key) {
		  byte[] dectyptedText = null;
		  try {
			  // get an RSA cipher object and print the provider
			  final Cipher cipher = Cipher.getInstance(ALGORITHM);

			  // decrypt the text using the private key
			  cipher.init(Cipher.DECRYPT_MODE, key);
			  dectyptedText = cipher.doFinal(text);
		  } 
		  catch (Exception ex) {
			  logger.error(ex.getMessage(), ex);
		  }

		  return new String(dectyptedText);
	  }
	  
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] hexToBytes(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	 public static String generateRandomPassword() {
		 String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		 String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		 String numbers = RandomStringUtils.randomNumeric(2);
		 String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
		 String totalChars = RandomStringUtils.randomAlphanumeric(2);
		 String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
				 .concat(numbers)
				 .concat(specialChar)
				 .concat(totalChars);
		 List<Character> pwdChars = combinedChars.chars()
				 .mapToObj(c -> (char) c)
				 .collect(Collectors.toList());
		 Collections.shuffle(pwdChars);
		 String password = pwdChars.stream()
				 .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				 .toString();
		 return password;	
	 }
}
