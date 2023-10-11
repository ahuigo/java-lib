package jproj.obj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
// import org.junit.Test;
// import org.junit.Test;//junit4

public class ClassStatic {

    private static int privateStaticMethod(int a, int b) {
        return a + b;
    }

    @Test
    public void testCallStaticMethod() {
        int n = privateStaticMethod(1, 2);
        assertEquals(3, n, "static method");
    }
}
