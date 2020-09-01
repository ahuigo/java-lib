import java.math.BigInteger; 
public class A
  {
    public static void main(String[] args)
    {
      var i = Integer.parseInt("0A0a",16); //\xA0A=2570
      System.out.println(i);
  }

}
