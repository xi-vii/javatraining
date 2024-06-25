import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IpAddress2Test {
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
    void getAddressTest() {
        IpAddress2 newIp = new IpAddress2("121.151.12.245");
        assertEquals("121.151.12.245", newIp.getAddress());
    }

    @Test
    void getIpRange2() {
        IpAddress2 address1 = new IpAddress2("121.151.11.254");
        IpAddress2 address2 = new IpAddress2("121.151.12.2");

        IpAddress2.getIpRange2(address1.getAddress(), address2.getAddress());

        String outputText = testOut.toString();

        assertEquals("121.151.11.255\r\n121.151.12.0\r\n121.151.12.1\r\n", outputText);
    }
}