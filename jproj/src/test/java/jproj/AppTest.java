package jproj;
// import jproj.App; // 同一个包下的类不需要import

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testMain() {
        App.main(new String[] {});
        assertEquals("Hello World!\n", outContent.toString());
    }

    @Test
    public void testCalcAdd() {
        assertEquals(2, 1 + 1, "calc");
    }
}