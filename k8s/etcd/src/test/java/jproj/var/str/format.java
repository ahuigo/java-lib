package jproj.var.str;

import java.text.MessageFormat;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang.StringEscapeUtils;

public class format {
    @Test
    public void testFormatRaw() {
        // raw string
        String s0 = """
                hi,
                world!
                """;
        String rawStr = String.format("'%s'", StringEscapeUtils.escapeJava(s0));
        System.out.println(rawStr);
    }

    @Test
    public void testFormatArg() {
        // 您好张三，晚上好！您目前余额：10.16元，积分：10
        String message = MessageFormat.format("您好{0}，晚上好！您目前余额：{1,number,#.##}元，积分：{2}", "张三", 10.155, 10);
        System.out.println(message);
    }
}
