package Connections;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;


public class EncodingSingleton {

    public static String encodePassword(String password)
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA"); 
    }
    catch(NoSuchAlgorithmException e)
    {
      System.err.println(e.getMessage());
    }
    try
    {
      md.update(password.getBytes("UTF-8")); 
    }
    catch(UnsupportedEncodingException e)
    {
      System.err.println(e.getMessage());
    }

    byte raw[] = md.digest(); 
    String hash = (new BASE64Encoder()).encode(raw); 
    return hash; 
  }

}
