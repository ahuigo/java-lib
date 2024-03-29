import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Aes {
	public static String encrypt(String input, String key){
	  byte[] crypted = null;
	  try{
	    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, skey);
	      crypted = cipher.doFinal(input.getBytes());
	    }catch(Exception e){
	    	System.out.println(e.toString());
	    }
	    return new String(Base64.encodeBase64(crypted));
	}

	public static String decrypt(String input, String key){
	    byte[] output = null;
	    try{
	      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
	      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	      cipher.init(Cipher.DECRYPT_MODE, skey);
	      output = cipher.doFinal(Base64.decodeBase64(input));
	    }catch(Exception e){
	      System.out.println(e.toString());
	    }
	    return new String(output);
	}

	public static void main(String[] args) {
	  String key = "1234567891234567";
	  String data = "example";
	  System.out.println("java.class.path:"+System.getProperty("java.class.path"));
	  System.out.println(Aes.decrypt(Aes.encrypt(data, key), key));
	  System.out.println(Aes.encrypt(data, key));
	}
}
