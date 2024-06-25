import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IpAddressNewTest {
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    // Перенаправляем стандартный вывод в ByteArrayOutputStream
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(testOut));
    }

    // Возвращаем стандартный вывод обратно
    @AfterEach
    void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    void getIpRangeTest() {
        IpAddressNew address1 = new IpAddressNew("121.151.11.254");
        IpAddressNew address2 = new IpAddressNew("121.151.12.2");

        address1.getIpRange(address2);

        String outputText = testOut.toString();

        assertEquals("121.151.11.255\r\n121.151.12.0\r\n121.151.12.1\r\n", outputText);
    }
}