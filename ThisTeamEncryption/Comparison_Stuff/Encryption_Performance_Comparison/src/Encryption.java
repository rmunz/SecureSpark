import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

/**
 * 
 */

/**
 * @author RyanWork
 *
 */
public class Encryption 
{
	private File src;
	private File dst;
	private int bufferSize;
	private int keySize = 0;
	private Cipher encryptor;
	private Cipher decryptor;
	private byte[] iv;
	private byte[] buffer;
	private FileInputStream inStream = null;
	private FileOutputStream outStream = null;
	private final int BYTE_LENGTH = 8; // Number of bits in a byte
	private final int AES = 128; // Key size for AES encryption
	private final int DES = 56; // Key size for DES encryption
	private final int DESEDE= 168; // Key size for DESEDE encryption
	private final int RC4 = 128; // Key size for RC4 encryption
	private final int GB = 1073741824; // Number of Bytes in 1 GB
	private static final String CWD = System.getProperty("user.dir");
	
	/**
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws IOException 
	 * 
	 */
	public Encryption(String src, String dst, int fileSize, String encryption, int bufferSize) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException 
	{
		this.bufferSize = bufferSize;
		this.buffer = new byte[this.bufferSize];
		this.src = new File(src);
		if (!this.src.exists()) 
		{
			this.src = genFile(src, fileSize);
		}
		this.dst = new File(dst);
		String delims = "/";
		String[] tokens = encryption.split(delims);
		
		KeyGenerator keyGen = null;
		SecretKey key = null;
		switch (tokens[0]) 
		{
		case "AES":
			keySize = AES;
			keyGen = KeyGenerator.getInstance("AES");
		    keyGen.init(keySize);
		    key = keyGen.generateKey();
			break;
		case "DES":
			keySize = DES;
			keyGen = KeyGenerator.getInstance("DES");
		    keyGen.init(keySize);
		    key = keyGen.generateKey();
			break;
		case "DESede":
			keySize = DESEDE;
			keyGen = KeyGenerator.getInstance("DESede");
		    keyGen.init(keySize);
		    key = keyGen.generateKey();
			break;
		case "RC4":
			keySize = RC4;
			keyGen = KeyGenerator.getInstance("RC4");
		    keyGen.init(keySize);
		    key = keyGen.generateKey();
			break;
		default:
			keySize = 0;
			break;
		}
		
		encryptor = Cipher.getInstance(encryption);
	    decryptor = Cipher.getInstance(encryption);
	    
	    if (key != null)
	    {
		    if (tokens.length > 1 && tokens[1].equals("CBC")) 
		    {
		    	iv = genIV();
		    	encryptor.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
		    	decryptor.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		    }
		    else 
		    {
		    	encryptor.init(Cipher.ENCRYPT_MODE, key);
		    	decryptor.init(Cipher.DECRYPT_MODE, key);
		    }
	    }
	    else
	    {
	    	System.out.println("Error, invalid key");
	    }
	}
	
	/**
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * 
	 */
	public void encrypt() throws IOException, IllegalBlockSizeException, BadPaddingException 
	{
		this.inStream = new FileInputStream(src);
		this.outStream = new FileOutputStream(dst);
		CipherOutputStream outEnc = new CipherOutputStream(outStream, encryptor);
		int count;
		while ((count = inStream.read(buffer)) > 0) 
		{
			outEnc.write(buffer, 0, count);
		}
		inStream.close();
		outEnc.close();
	}
	
	/**
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws IOException 
	 * 
	 */
	public void decrypt() throws IllegalBlockSizeException, BadPaddingException, IOException 
	{
		this.inStream = new FileInputStream(dst);
		File tmp = new File(dst.getAbsolutePath() + "tmp.txt");
		this.outStream = new FileOutputStream(tmp);
		CipherInputStream inEnc = new CipherInputStream(inStream, decryptor);
		int count;
		while ((count = inEnc.read(buffer)) > 0) 
		{
			outStream.write(buffer, 0, count);
		}
		inEnc.close();
		outStream.close();
		Files.delete(dst.toPath());
		tmp.renameTo(dst);
	}

	private byte[] genIV() 
	{
		byte[] iv;
		if (keySize / BYTE_LENGTH <= 8)
		{
			iv = new byte[8];
		}
		else
		{
			iv = new byte[keySize / BYTE_LENGTH];
		}
	    SecureRandom prng = new SecureRandom();
	    prng.nextBytes(iv);
	    return iv;
	}
	
	private File genFile(String src, int size) throws IOException
	{
		int fSize = size * GB;
		File moby = new File(CWD + "\\mobydick.txt");
		byte[] mobyBuff = new byte[this.bufferSize];
		FileInputStream mobyStream = new FileInputStream(moby);
		mobyStream.read(mobyBuff);
		mobyStream.close();
		File genFile = new File(src);
		FileOutputStream genStream = new FileOutputStream(genFile);
		int bytes = 0;
		while (bytes <= fSize)
		{
			genStream.write(mobyBuff, 0, this.bufferSize);
			bytes += this.bufferSize;
		}
		genStream.close();
		return genFile;
	}
}
