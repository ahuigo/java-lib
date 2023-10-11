package jproj.var.array;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class arr {

    @Test
    public void testArrayDefine2() {

        String[] arr = { "a", "b", "c" };

        // via Arrarys
        System.out.println(Arrays.stream(arr).anyMatch("a"::equals));

        // via List
        java.util.List<String> list = java.util.Arrays.asList(arr);
        System.out.println(list.contains("a"));

    }

}
