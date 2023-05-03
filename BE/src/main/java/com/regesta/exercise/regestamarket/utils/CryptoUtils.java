package com.regesta.exercise.regestamarket.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public  class CryptoUtils {
	
	private static final String CIPHER_ALG = "AES";

	private SecretKeySpec secretKey;
	
	private Cipher cipher;

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public CryptoUtils(String secret, int length, String algorithm)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		byte[] key = new byte[length];
		key = fixSecret(secret, length);
		this.secretKey = new SecretKeySpec(key, algorithm);
		this.cipher = Cipher.getInstance(algorithm);
	}

	private static byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
		/*if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += " ";
			}
		}*/
		return s.substring(0, length).getBytes("UTF-8");
	}

	public void encryptFile(File f)
			throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
		this.writeToFile(f);
	}

	public void decryptFile(File f)
			throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
		this.writeToFile(f);
	}

	public void writeToFile(File f) throws IOException, IllegalBlockSizeException, BadPaddingException {
		FileInputStream in = new FileInputStream(f);
		byte[] input = new byte[(int) f.length()];
		in.read(input);

		FileOutputStream out = new FileOutputStream(f);
		byte[] output = this.cipher.doFinal(input);
		out.write(output);

		out.flush();
		out.close();
		in.close();
	}
	
	public static String encryptToString(String unencryptedString, String secret)  {
		String encryptedString = null;
		Cipher cipherInstance = null;
		SecretKeySpec mySecretKey;
		
		try {
			byte[] key = fixSecret(secret, secret.length());
			mySecretKey = new SecretKeySpec(key, CIPHER_ALG);

			cipherInstance = Cipher.getInstance(CIPHER_ALG);
			cipherInstance.init(Cipher.ENCRYPT_MODE, mySecretKey);
			
			if(unencryptedString != null){
				byte[] output = cipherInstance.doFinal(unencryptedString.getBytes());
				encryptedString = CypherUtils.bytesToHex(output);
			}
		}
		catch (Exception ex) {
			logger.error("Error while encrypting string", ex);
		}
			
		return encryptedString;
	}

	public static String decryptFromString(String encryptedString, String secret) {
		
		String unencryptedString = null;
		Cipher cipherInstance = null;
		
		SecretKeySpec mySecretKey;
		
		try {
			byte[] key = fixSecret(secret, secret.length());
			mySecretKey = new SecretKeySpec(key, CIPHER_ALG);
			

			cipherInstance = Cipher.getInstance(CIPHER_ALG);
			cipherInstance.init(Cipher.DECRYPT_MODE, mySecretKey);
			
			if(encryptedString != null){
				byte[] output = cipherInstance.doFinal(CypherUtils.hexToBytes(encryptedString));
				unencryptedString = new String(output);
			}
		} 
		catch (Exception ex) {
			logger.error("Error while decrypting string", ex);
			return null;
		}
		
		
		return unencryptedString;
	}
}
