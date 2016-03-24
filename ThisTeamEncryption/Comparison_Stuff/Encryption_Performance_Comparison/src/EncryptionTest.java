import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 */

/**
 * @author RyanWork
 *
 */
public class EncryptionTest {
	private String mobyPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
	private String smallPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Small.txt";
	private String mediumPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Medium.txt";
	private String largePath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Large.txt";
	
	private String mobyEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
	private String smallEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Smallenc.txt";
	private String mediumEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Mediumenc.txt";
	private String largeEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Largeenc.txt";
	
//	private String mobyPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
//	private String smallPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
//	private String mediumPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
//	private String largePath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
//	
//	private String mobyEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
//	private String smallEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
//	private String mediumEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
//	private String largeEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
	
	private String dataPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Data.csv";
	
	private File moby = new File(mobyPath);
	private File small = new File(smallPath);
	private File medium = new File(mediumPath);
	private File large = new File(largePath);
	
	private File mobyEnc = new File(mobyEncPath);
	private File smallEnc = new File(smallEncPath);
	private File mediumEnc = new File(mediumEncPath);
	private File largeEnc = new File(largeEncPath);
	
	private long startTime;
	private long endTime;
	private long duration;
	
	private static final String NEW_LINE = "\n";
	private static final String FILE_HEADER = "File Name (Size),"
			+ "AES/CBC Encryption Time,"
			+ "AES/CBC Decryption Time,"
			+ "AES/ECB Encryption Time,"
			+ "AES/ECB Decryption Time,"
			+ "DES/CBC Encryption Time,"
			+ "DES/CBC Decryption Time,"
			+ "DES/ECB Encryption Time,"
			+ "DES/ECB Decryption Time,"
			+ "DESede/ECB Encryption Time,"
			+ "DESede/ECB Decryption Time,"
			+ "RC4 Encryption Time,"
			+ "RC4 Decryption Time";
	private static String mobyTime = "Moby Dick (0.0012 GB),";
	private static String smallTime = "Small (1 GB),";
	private static String mediumTime = "Medium (5 GB),";
	private static String largeTime = "Large (10 GB),";
	
	private static int numTests = 0;
	private static final int TOTAL_NUM_TESTS = 24;
	
	/**
	 * 
	 */
	@Before
	public void setUp()
	{
		// Intentionally left blank
	}
	
	////////////////////////////////////////////////////////////////////////////
	// AES/CBC Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Moby AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/CBC Encryption Complete");
		System.out.println("Moby AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}
	
	@Test
	public void smallAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Small AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/CBC Encryption Complete");
		System.out.println("Small AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Medium AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/CBC Encryption Complete");
		System.out.println("Medium AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Large AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/CBC Encryption Completed");
		System.out.println("Large AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/CBC Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// AES/ECB Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Moby AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/ECB Encryption Complete");
		System.out.println("Moby AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}
	
	@Test
	public void smallAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Small AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/ECB Encryption Complete");
		System.out.println("Small AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/ECB Encryption Complete");
		System.out.println("Medium AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Large AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/ECB Encryption Completed");
		System.out.println("Large AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DES/CBC Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "DES/CBC/PKCS5Padding", 8192);
		System.out.println("Moby DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/CBC Encryption Complete");
		System.out.println("Moby DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}
	
	@Test
	public void smallDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Small DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/CBC Encryption Complete");
		System.out.println("Small DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Medium DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/CBC Encryption Complete");
		System.out.println("Medium DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Large DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/CBC Encryption Completed");
		System.out.println("Large DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/CBC Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DES/ECB Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "DES/ECB/PKCS5Padding", 8192);
		System.out.println("Moby DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/ECB Encryption Complete");
		System.out.println("Moby DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}
	
	@Test
	public void smallDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Small DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/ECB Encryption Complete");
		System.out.println("Small DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/ECB Encryption Complete");
		System.out.println("Medium DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Large DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/ECB Encryption Completed");
		System.out.println("Large DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DESede/ECB Testing Area                                                //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "DESede/ECB/PKCS5Padding", 8192);
		System.out.println("Moby DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DESede/ECB Encryption Complete");
		System.out.println("Moby DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DESede/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}
	
	@Test
	public void smallDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Small DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DESede/ECB Encryption Complete");
		System.out.println("Small DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DESede/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DESede/ECB Encryption Complete");
		System.out.println("Medium DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DESede/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Large DESede/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DESede/ECB Encryption Completed");
		System.out.println("Large DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DESede/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// RC4 Testing Area                                                       //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, "RC4", 8192);
		System.out.println("Moby RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby RC4 Encryption Complete");
		System.out.println("Moby RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime += duration + ",";
		assertTrue(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby RC4 Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mobyEnc.toPath());
	}	
	
	@Test
	public void smallRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(smallPath, smallEncPath, "RC4", 8192);
		System.out.println("Small RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small RC4 Encryption Complete");
		System.out.println("Small RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime += duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small RC4 Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
	}
	
	@Test
	public void mediumRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, "RC4", 8192);
		System.out.println("Medium RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium RC4 Encryption Complete");
		System.out.println("Medium RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime += duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium RC4 Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
	}
	
	@Test
	public void largeRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, "RC4", 8192);
		System.out.println("Large RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large RC4 Encryption Completed");
		System.out.println("Large RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime += duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large RC4 Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Helper Functions                                                       //
	////////////////////////////////////////////////////////////////////////////
	private void writeData() throws IOException
	{
		System.out.println("Writing Data...");
		FileWriter writer = new FileWriter(dataPath);
		writer.append(FILE_HEADER);
		writer.append(NEW_LINE);
		writer.append(mobyTime);
		writer.append(NEW_LINE);
		writer.append(smallTime);
		writer.append(NEW_LINE);
		writer.append(mediumTime);
		writer.append(NEW_LINE);
		writer.append(largeTime);
		writer.append(NEW_LINE);
		writer.close();
		System.out.println("Finished Writing Data...");
	}
}
