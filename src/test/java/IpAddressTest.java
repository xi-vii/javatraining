import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IpAddressTest {
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
    void mainTest() {
        provideInput("10.15.1\n10.15.3\n");
        SimpleSum.main(new String[0]);
        String outputText = testOut.toString();
        String rightOutputText = outputText.substring(outputText.indexOf("IP диапазона"));

        assertEquals("IP диапазона:\r\n10.15.2\r\n", rightOutputText);
    }

    @Test
    void getIndexTest() {
        assertEquals(1, IpAddress.getIndex("192.168.0.1"));
    }

    @Test
    void getNewIpTest() {
        assertEquals("192.168.0.3", IpAddress.getNewIp("192.168.0.1", 3));
    }
}