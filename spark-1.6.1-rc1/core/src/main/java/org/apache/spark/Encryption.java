package org.apache.spark;

import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

public class Encryption {
  private final int BYTE_LENGTH = 8;
  private Cipher encryptor;
  private Cipher decryptor;
  private int keySize;
  private byte[] iv;

  public Encryption(SecretKey key, int keySize) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
    this.keySize = keySize;
    iv = genIV();
    encryptor = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    decryptor = Cipher.getInstance("AES/CBC/PKCS5PADDING");

    encryptor.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
    decryptor.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
  }

  public byte[] encrypt(byte[] clear) throws IllegalBlockSizeException, BadPaddingException {
    return encryptor.doFinal(clear);
  }

  public byte[] decrypt(byte[] cipher) throws IllegalBlockSizeException, BadPaddingException {
    return decryptor.doFinal(cipher);
  }

  private byte[] genIV() {
    byte[] iv = new byte[keySize / BYTE_LENGTH];
    SecureRandom prng = new SecureRandom();
    prng.nextBytes(iv);
    return iv;
  }

  public static byte[] XOR(byte key, String line) {
    try {
      byte[] clear = line.getBytes("UTF8");
      byte[] clouded = new byte[clear.length];
      int i = 0;
      for(byte b : clear) {
        clouded[i++] = (byte) (b ^ key);
      }

      return clouded;//java.util.Base64.getEncoder().encodeToString(clouded);
    } catch(java.io.UnsupportedEncodingException e) {
	    return null;
    }
  }

  public static String XOR_bytesIn(byte key, byte[] clouded) {
    byte[] clear = new byte[clouded.length];
    int i = 0;
    for(byte b : clouded) {
      clear[i++] = (byte) (b ^ key);
    }

    return new String(clear);
  }
}
