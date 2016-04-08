package org.apache.spark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.lang.IllegalArgumentException;

/**
 * 
 */

/**
 * @author RyanWork
 *
 */
public class Encryption 
{
	private static final int BYTE_LENGTH = 8; // Number of bits in a byte
	private static final int AES = 128; // Key size for AES encryption
	private static final int GB = 1073741824; // Number of Bytes in 1 GB
	private static final String ENC_TYPE = "AES/CBC/PKCS5PADDING";
	/**
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * 
	 */
	public static String encrypt(String clear, SecretKey key, byte[] iv) throws IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException
	{
		Cipher cipher = Cipher.getInstance(ENC_TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		byte[] clear_bytes = clear.getBytes("UTF-8");
		byte[] cipher_bytes = cipher.doFinal(clear_bytes);
		String output = java.util.Base64.getEncoder().encodeToString(cipher_bytes);
		return output;
	}
	
	/**
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws IOException 
	 * 
	 */
	public static String decrypt(String cipher_string, SecretKey key, byte[] iv) throws IllegalBlockSizeException, BadPaddingException, IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException
	{
		Cipher cipher = Cipher.getInstance(ENC_TYPE);
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		byte[] cipher_bytes = java.util.Base64.getDecoder().decode(cipher_string);
		byte[] clear = cipher.doFinal(cipher_bytes);
		String output = new String(clear, "UTF-8");
		return output;
	}

	public static byte[] genIV() 
	{
		byte[] iv;
		if (AES / BYTE_LENGTH <= 8)
		{
			iv = new byte[8];
		}
		else
		{
			iv = new byte[AES / BYTE_LENGTH];
		}
	    SecureRandom prng = new SecureRandom();
	    prng.nextBytes(iv);
	    return iv;
	}
}
