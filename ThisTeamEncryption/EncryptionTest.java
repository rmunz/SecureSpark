import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;

import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;

public class EncryptionTest {
  public static void main(String[] args) {
    byte[] toEnc = new String("Hello, world!").getBytes();
    byte[] toDec;

    int keySize = 128;
    
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(keySize);
      SecretKey key = keyGen.generateKey();
      
      Encryption encryption = new Encryption(key, keySize);
      
      toDec = encryption.encrypt(toEnc);
      System.out.println("cipher: " + new String(toDec));
      System.out.println("clear: " + new String(encryption.decrypt(toDec)));
    }
    catch(NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    catch(NoSuchPaddingException e) {
      e.printStackTrace();
    }
    catch(InvalidKeyException e) {
      e.printStackTrace();
    }
    catch(InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    }
    catch(IllegalBlockSizeException e) {
      e.printStackTrace();
    }
    catch(BadPaddingException e) {
      e.printStackTrace();
    }
  }
}
