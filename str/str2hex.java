import java.math.BigInteger; 
public class A
  {
    public static void main(String[] args)
    {
      String newline = "01\n";
      System.out.println("01\\n:" + toHex2(newline));
  }

  public static String toHex(String arg) {
    return String.format("%x", new BigInteger(arg.getBytes()));
  }

  public static String toHex2(String s) {
    StringBuilder str = new StringBuilder();
    var s1 = "";
    var ba = s.getBytes();
    for(int i = 0; i < ba.length; i++)
        //str.append(String.format("%02x", ba[i]));
        s1 = s1 + String.format("%02x", ba[i]);
    return s1;
    //return str.toString();
  }


}
