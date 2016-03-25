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
	private static final String CWD = System.getProperty("user.dir");
	private String mobyPath = CWD + "\\mobydick.txt";
	private String smallPath = CWD + "\\Small.txt";
	private String mediumPath = CWD + "\\Medium.txt";
	private String largePath = CWD + "\\Large.txt";
	
	private String mobyEncPath = CWD + "\\mobydickenc.txt";
	private String smallEncPath = CWD + "\\Smallenc.txt";
	private String mediumEncPath = CWD + "\\Mediumenc.txt";
	private String largeEncPath = CWD + "\\Largeenc.txt";
	
//	private String mobyPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydick.txt";
//	private String smallPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Small.txt";
//	private String mediumPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Medium.txt";
//	private String largePath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Large.txt";
//	
//	private String mobyEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\mobydickenc.txt";
//	private String smallEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Smallenc.txt";
//	private String mediumEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Mediumenc.txt";
//	private String largeEncPath = "C:\\Users\\RyanWork\\Documents\\School\\NS_Capstone\\Test_Files\\Largeenc.txt";
	
	private String dataPath = CWD + "\\Data.csv";
	
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
	
	private final int MOBY = 0;
	private final int SMALL = 1;
	private final int MEDIUM = 5;
	private final int LARGE = 10;
	
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
	private static String[] mobyTime = new String[13];
	private static String[] smallTime = new String[13];
	private static String[] mediumTime = new String[13];
	private static String[] largeTime = new String[13];
	
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
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Moby AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[1] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/CBC Encryption Complete");
		System.out.println("Moby AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[2] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Small AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[1] = duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/CBC Encryption Complete");
		System.out.println("Small AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[2] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Medium AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[1] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/CBC Encryption Complete");
		System.out.println("Medium AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[2] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeAESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "AES/CBC/PKCS5PADDING", 8192);
		System.out.println("Large AES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[1] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/CBC Encryption Completed");
		System.out.println("Large AES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[2] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/CBC Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// AES/ECB Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Moby AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[3] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby AES/ECB Encryption Complete");
		System.out.println("Moby AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[4] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Small AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[3] = duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/ECB Encryption Complete");
		System.out.println("Small AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[4] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small AES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[3] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/ECB Encryption Complete");
		System.out.println("Medium AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[4] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium AES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeAESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "AES/ECB/PKCS5PADDING", 8192);
		System.out.println("Large AES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[3] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/ECB Encryption Completed");
		System.out.println("Large AES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[4] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large AES/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DES/CBC Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "DES/CBC/PKCS5Padding", 8192);
		System.out.println("Moby DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[5] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/CBC Encryption Complete");
		System.out.println("Moby DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[6] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Small DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[5] = duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/CBC Encryption Complete");
		System.out.println("Small DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[6] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Medium DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[5] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/CBC Encryption Complete");
		System.out.println("Medium DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[6] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/CBC Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeDESCBCEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "DES/CBC/PKCS5PADDING", 8192);
		System.out.println("Large DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[5] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/CBC Encryption Completed");
		System.out.println("Large DES/CBC Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[6] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/CBC Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DES/ECB Testing Area                                                   //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "DES/ECB/PKCS5Padding", 8192);
		System.out.println("Moby DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[7] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DES/ECB Encryption Complete");
		System.out.println("Moby DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[8] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Small DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[7] = duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/ECB Encryption Complete");
		System.out.println("Small DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[8] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium DES/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[7] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/ECB Encryption Complete");
		System.out.println("Medium DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[8] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DES/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeDESECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "DES/ECB/PKCS5PADDING", 8192);
		System.out.println("Large DES/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[7] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/ECB Encryption Completed");
		System.out.println("Large DES/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[8] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DES/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// DESede/ECB Testing Area                                                //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "DESede/ECB/PKCS5Padding", 8192);
		System.out.println("Moby DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[9] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby DESede/ECB Encryption Complete");
		System.out.println("Moby DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[10] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Small DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[9] = duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DESede/ECB Encryption Complete");
		System.out.println("Small DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[10] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small DESede/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Medium DESede/ECB Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[9] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DESede/ECB Encryption Complete");
		System.out.println("Medium DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[10] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium DESede/ECB Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeDESedeECBEncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "DESede/ECB/PKCS5PADDING", 8192);
		System.out.println("Large DESede/CBC Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[9] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DESede/ECB Encryption Completed");
		System.out.println("Large DESede/ECB Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[10] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large DESede/ECB Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// RC4 Testing Area                                                       //
	////////////////////////////////////////////////////////////////////////////
	@Test
	public void mobyRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mobyPath, mobyEncPath, MOBY, "RC4", 8192);
		System.out.println("Moby RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[11] = duration + ",";
		assertFalse(FileUtils.contentEquals(moby, mobyEnc));
		System.out.println("Moby RC4 Encryption Complete");
		System.out.println("Moby RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mobyTime[12] = duration + ",";
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
		Encryption enc = new Encryption(smallPath, smallEncPath, SMALL, "RC4", 8192);
		System.out.println("Small RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[11] =duration + ",";
		assertFalse(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small RC4 Encryption Complete");
		System.out.println("Small RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		smallTime[12] = duration + ",";
		assertTrue(FileUtils.contentEquals(small, smallEnc));
		System.out.println("Small RC4 Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(smallEnc.toPath());
		Files.delete(small.toPath());
	}
	
	@Test
	public void mediumRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(mediumPath, mediumEncPath, MEDIUM, "RC4", 8192);
		System.out.println("Medium RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[11] = duration + ",";
		assertFalse(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium RC4 Encryption Complete");
		System.out.println("Medium RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		mediumTime[12] = duration + ",";
		assertTrue(FileUtils.contentEquals(medium, mediumEnc));
		System.out.println("Medium RC4 Decryption Complete");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(mediumEnc.toPath());
		Files.delete(medium.toPath());
	}
	
	@Test
	public void largeRC4EncryptionTest() throws IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException 
	{
		Encryption enc = new Encryption(largePath, largeEncPath, LARGE, "RC4", 8192);
		System.out.println("Large RC4 Encryption Started");
		startTime = System.nanoTime();
		enc.encrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[11] = duration + ",";
		assertFalse(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large RC4 Encryption Completed");
		System.out.println("Large RC4 Decryption Started");
		startTime = System.nanoTime();
		enc.decrypt();
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;  //milliseconds.
		largeTime[12] = duration + ",";
		assertTrue(FileUtils.contentEquals(large, largeEnc));
		System.out.println("Large RC4 Decryption Completed");
		numTests += 1;
		if (numTests == TOTAL_NUM_TESTS) {
			writeData();
		}
		Files.delete(largeEnc.toPath());
		Files.delete(large.toPath());
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Helper Functions                                                       //
	////////////////////////////////////////////////////////////////////////////
	private void writeData() throws IOException
	{
		System.out.println("Writing Data...");
		mobyTime[0] = "Moby Dick (0.0012 GB),";
		smallTime[0] = "Small (1 GB),";
		mediumTime[0] = "Medium (5 GB),";
		largeTime[0] = "Large (10 GB),";
		FileWriter writer = new FileWriter(dataPath);
		String mobyT = mobyTime[0];
		String smallT = smallTime[0];
		String mediumT = mediumTime[0];
		String largeT = largeTime[0];
		for (int i = 1; i < 13; i++)
		{
			mobyT += mobyTime[i];
			smallT += smallTime[i];
			mediumT += mediumTime[i];
			largeT += largeTime[i];
		}
		writer.append(FILE_HEADER);
		writer.append(NEW_LINE);
		writer.append(mobyT);
		writer.append(NEW_LINE);
		writer.append(smallT);
		writer.append(NEW_LINE);
		writer.append(mediumT);
		writer.append(NEW_LINE);
		writer.append(largeT);
		writer.append(NEW_LINE);
		writer.close();
		System.out.println("Finished Writing Data...");
	}
}
