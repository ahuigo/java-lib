import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
public class PatternCompileExample1 {  
    public static void main(String[] args) {  
        String url = "ahuigo.com";
        Pattern  p=Pattern.compile("ahuigo.com");
        Matcher m=p.matcher(url);
        if(m.find())
        System.out.println(" Result :  "+m);
        System.out.println("matches:" + m.matches()); //完全匹配
    }
}

