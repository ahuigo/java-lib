package jproj;
// import jproj.App; // 同一个包下的类不需要import

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppCaptureStdoutTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        boolean captureStdouterr = true;
        if (captureStdouterr) {
            System.setOut(new PrintStream(outContent));
        }
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testMain() {
        // Vscode 中Debug Console 这个tab 才可以看到stdout 输出
        App.main(new String[] {});
        assertEquals("Hello World!\n", outContent.toString());
    }

    @Test
    public void testCalcAdd() {
        assertEquals(2, 1 + 1, "calc");
    }
}
