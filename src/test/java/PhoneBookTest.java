import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void mainTest1() {
        String testName = "Петров П.П.";
        provideInput(testName);

        PhoneBook.main(new String[0]);
        assertEquals("1. +8 800 2000 700\n", testOut.toString());
    }

    @Test
    void mainTest2() {
        String testName = "Иванов И.И.";
        provideInput(testName);

        PhoneBook.main(new String[0]);
        assertEquals("1. +8 800 2000 500\n2. +8 800 200 600\n", testOut.toString());
    }
}